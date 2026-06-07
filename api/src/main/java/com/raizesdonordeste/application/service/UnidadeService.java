package com.raizesdonordeste.application.service;

import com.raizesdonordeste.api.request.UnidadeRequest;
import com.raizesdonordeste.api.response.UnidadeResponse;
import com.raizesdonordeste.domain.entity.Unidade;
import com.raizesdonordeste.domain.repository.UnidadeRepository;
import com.raizesdonordeste.infraestructure.config.BusinessException;
import com.raizesdonordeste.infraestructure.config.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UnidadeService {

    private final UnidadeRepository unidadeRepository;

    public UnidadeResponse criar (
            UnidadeRequest request
    ){
        Unidade unidade = Unidade.builder()
                .nome(request.nome())
                .build();

        unidade = unidadeRepository.save(unidade);

        return  converter(unidade);
    }

    public List<UnidadeResponse> listar() {

        return unidadeRepository.findAll()
                .stream()
                .map(this::converter)
                .toList();
    }

    public UnidadeResponse buscarPorId(
            Long id
    ) {
        Unidade unidade = unidadeRepository.findById(id)
                .orElseThrow(() ->
                       new BusinessException("Unidade nao encontrada"));

        return converter(unidade);
    }

    public UnidadeResponse atualizar(
            Long id,
            UnidadeRequest request
    ) {
        Unidade unidade = unidadeRepository.findById(id)
                .orElseThrow(() ->
                       new ResourceNotFoundException("Unidade nao encontrada"));

        unidade.setNome(request.nome());

        unidade = unidadeRepository.save(unidade);

        return converter(unidade);
    }

    public void deletar(Long id) {
        Unidade unidade = unidadeRepository.findById(id)
                .orElseThrow(() ->
                       new ResourceNotFoundException("Unidade nao encontrada"));

        unidadeRepository.delete(unidade);
    }

    private UnidadeResponse converter(
            Unidade unidade
    ) {
        return new UnidadeResponse(
                unidade.getId(),
                unidade.getNome()
        );
    }
}
