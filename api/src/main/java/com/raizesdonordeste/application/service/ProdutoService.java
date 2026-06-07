package com.raizesdonordeste.application.service;

import com.raizesdonordeste.api.request.ProdutoRequest;
import com.raizesdonordeste.api.response.ProdutoResponse;
import com.raizesdonordeste.domain.entity.Produto;
import com.raizesdonordeste.domain.repository.ProdutoRepository;
import com.raizesdonordeste.infraestructure.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProdutoService {

    private final ProdutoRepository produtoRepository;

    public ProdutoResponse criar (
            ProdutoRequest request
    ) {
        Produto produto = Produto.builder()
                .nome(request.nome())
                .preco(request.preco())
                .build();

        produto = produtoRepository.save(produto);

        return converter(produto);
    }

    public List<ProdutoResponse> listar() {

        return produtoRepository.findAll()
                .stream()
                .map(this::converter)
                .toList();
    }

    public ProdutoResponse buscarPorId(Long id) {

        Produto produto = produtoRepository.findById(id)
                .orElseThrow(() ->new ResourceNotFoundException("Produto não encontrado"));

        return converter(produto);
    }

    public ProdutoResponse atualizar(
            Long id,
            ProdutoRequest request
    ) {
        Produto produto = produtoRepository.findById(id)
                .orElseThrow(() ->new ResourceNotFoundException("Produto não encontrado"));

        produto.setNome(request.nome());
        produto.setPreco(request.preco());

        produto = produtoRepository.save(produto);

        return  converter(produto);
    }

    public void deletar(Long id){
        Produto produto = produtoRepository.findById(id)
                .orElseThrow(() ->new ResourceNotFoundException("Produto não encontrado"));

        produtoRepository.delete(produto);
    }


    private ProdutoResponse converter (
            Produto produto
    ) {
        return new ProdutoResponse(
                produto.getId(),
                produto.getNome(),
                produto.getPreco()
        );
    }
}
