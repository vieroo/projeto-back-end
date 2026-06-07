package com.raizesdonordeste.application.service;

import com.raizesdonordeste.api.request.EstoqueRequest;
import com.raizesdonordeste.api.response.EstoqueResponse;
import com.raizesdonordeste.domain.entity.Estoque;
import com.raizesdonordeste.domain.entity.Produto;
import com.raizesdonordeste.domain.entity.Unidade;
import com.raizesdonordeste.domain.repository.EstoqueRepository;
import com.raizesdonordeste.domain.repository.ProdutoRepository;
import com.raizesdonordeste.domain.repository.UnidadeRepository;
import com.raizesdonordeste.infraestructure.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EstoqueService {

    private final EstoqueRepository estoqueRepository;
    private final ProdutoRepository produtoRepository;
    private final UnidadeRepository unidadeRepository;

    public EstoqueResponse criar(
            EstoqueRequest request
    ) {

        Produto produto = produtoRepository.findById(
                request.produtoId()
        ).orElseThrow(() ->
               new ResourceNotFoundException("Produto não encontrado"));

        Unidade unidade = unidadeRepository.findById(
                request.unidadeId()
        ).orElseThrow(() ->
               new ResourceNotFoundException("Unidade não encontrada"));

        Estoque estoque = Estoque.builder()
                .produto(produto)
                .unidade(unidade)
                .quantidade(request.quantidade())
                .build();

        estoque = estoqueRepository.save(estoque);

        return  converter(estoque);
    }

    public List<EstoqueResponse> listar() {

        return estoqueRepository.findAll()
                .stream()
                .map(this::converter)
                .toList();
    }

    public EstoqueResponse buscarPorId(Long id) {

        Estoque estoque = estoqueRepository.findById(id)
                .orElseThrow(() ->
                       new ResourceNotFoundException("Estoque nao encontrado"));

        return converter(estoque);
    }

    public EstoqueResponse atualizar(
            Long id,
            EstoqueRequest request
    ) {

        Estoque estoque = estoqueRepository.findById(id)
                .orElseThrow(() ->
                       new ResourceNotFoundException("Estoque nao encontrado"));

        Produto produto = produtoRepository.findById(
                request.produtoId()
        ).orElseThrow();

        Unidade unidade = unidadeRepository.findById(
                request.unidadeId()
        ).orElseThrow();

        estoque.setProduto(produto);
        estoque.setUnidade(unidade);
        estoque.setQuantidade(request.quantidade());

        estoque = estoqueRepository.save(estoque);

        return converter(estoque);
    }

    public void deletar(Long id) {

        Estoque estoque = estoqueRepository.findById(id)
                .orElseThrow(() ->
                       new ResourceNotFoundException("Estoque nao encontrado"));

        estoqueRepository.delete(estoque);
    }

    private EstoqueResponse converter(
            Estoque estoque
    ) {

        return new EstoqueResponse(
                estoque.getId(),
                estoque.getProduto().getId(),
                estoque.getProduto().getNome(),
                estoque.getUnidade().getId(),
                estoque.getUnidade().getNome(),
                estoque.getQuantidade()
        );
    }
}
