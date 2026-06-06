package com.raizesdonordeste.application.service;

import com.raizesdonordeste.api.request.ItemPedidoRequest;
import com.raizesdonordeste.api.request.PedidoRequest;
import com.raizesdonordeste.api.response.ItemPedidoResponse;
import com.raizesdonordeste.api.response.PedidoResponse;
import com.raizesdonordeste.domain.entity.*;
import com.raizesdonordeste.domain.enums.StatusPedido;
import com.raizesdonordeste.domain.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor

public class PedidoService {

    private final PedidoRepository pedidoRepository;
    private final ItemPedidoRepository itemPedidoRepository;
    private final UsuarioRepository usuarioRepository;
    private final ProdutoRepository produtoRepository;
    private final EstoqueRepository estoqueRepository;
    private final FidelidadeRepository fidelidadeRepository;
    private final PagamentoMockService pagamentoMockService;

    @Transactional
    public PedidoResponse criar(PedidoRequest request) {

        Usuario cliente = usuarioRepository.findById(request.clientId())
                .orElseThrow(() ->
                        new RuntimeException("Cliente não encontrado"));

        Pedido pedido = Pedido.builder()
                .cliente(cliente)
                .status(StatusPedido.AGUARDANDO_PAGAMENTO)
                .canalPedido(request.canalPedido())
                .total(BigDecimal.ZERO)
                .build();

        pedido = pedidoRepository.save(pedido);

        List<ItemPedidoResponse> itensResponse = new ArrayList<>();

        BigDecimal total = BigDecimal.ZERO;

        for (ItemPedidoRequest itemRequest : request.itens()) {

            Produto produto = produtoRepository.findById(itemRequest.produtoId())
                    .orElseThrow(() ->
                            new RuntimeException("Produto não encontrado"));

            Estoque estoque = estoqueRepository
                    .findByProdutoIdAndUnidadeId(
                        produto.getId(),
                        request.unidadeId()
                    )
                    .orElseThrow(() ->
                            new RuntimeException("Estoque não encontrado"));

            if (estoque.getQuantidade() < itemRequest.quantidade()) {
                throw new RuntimeException(
                        "Estoque insuficiente para o produto: "
                                + produto.getNome()
                );
            }

            estoque.setQuantidade(
                    estoque.getQuantidade() - itemRequest.quantidade()
            );

            estoqueRepository.save(estoque);

            ItemPedido itemPedido = ItemPedido.builder()
                    .pedido(pedido)
                    .produto(produto)
                    .quantidade(itemRequest.quantidade())
                    .build();

            itemPedidoRepository.save(itemPedido);

            total = total.add(
                    produto.getPreco().multiply(
                            BigDecimal.valueOf(itemRequest.quantidade())
                    )
            );

            itensResponse.add(
                    new ItemPedidoResponse(
                            produto.getId(),
                            produto.getNome(),
                            itemRequest.quantidade()
                    )
            );
        }

        pedido.setTotal(total);

        boolean pagamentoAprovado =
                pagamentoMockService.processarPagamento();

        if (pagamentoAprovado) {

            pedido.setStatus(StatusPedido.PAGO);

            int pontos = total.intValue() / 10;

            Fidelidade fidelidade =
                    fidelidadeRepository
                            .findByClienteId(cliente.getId())
                            .orElse(
                                    Fidelidade.builder()
                                            .cliente(cliente)
                                            .pontos(0)
                                            .build()
                            );


            fidelidade.setPontos(
                    fidelidade.getPontos() + pontos
            );

            fidelidadeRepository.save(fidelidade);

        } else {

            pedido.setStatus(StatusPedido.CANCELADO);
        }

        pedido = pedidoRepository.save(pedido);

        return new PedidoResponse(
                pedido.getId(),
                pedido.getStatus(),
                pedido.getCanalPedido(),
                pedido.getTotal(),
                itensResponse
        );

    }

    public PedidoResponse atualizarStatus(
            Long id,
            StatusPedido status
    ) {

        Pedido pedido = pedidoRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Pedido não encontrado"));

        if (pedido.getStatus() == StatusPedido.CANCELADO) {
            throw new RuntimeException(
                    "Pedido cancelado não pode ser atualizado"
            );
        }

        pedido.setStatus(status);

        pedidoRepository.save(pedido);

        List<ItemPedidoResponse> itens = itemPedidoRepository
                .findByPedidoId(id)
                .stream()
                .map(item -> new ItemPedidoResponse(
                        item.getProduto().getId(),
                        item.getProduto().getNome(),
                        item.getQuantidade()
                ))
                .toList();

        return new PedidoResponse(
                pedido.getId(),
                pedido.getStatus(),
                pedido.getCanalPedido(),
                pedido.getTotal(),
                itens
        );
    }

}
