package com.raizesdonordeste.application.service;

import com.raizesdonordeste.domain.entity.Auditoria;
import com.raizesdonordeste.domain.repository.AuditoriaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AuditoriaService {

    private final AuditoriaRepository auditoriaRepository;

    public void registrar(
            String usuario,
            String acao,
            String entidade,
            Long entidadeId
    ) {

        Auditoria auditoria = Auditoria.builder()
                .usuario(usuario)
                .acao(acao)
                .entidade(entidade)
                .entidadeId(entidadeId)
                .dataHora(LocalDateTime.now())
                .build();

        auditoriaRepository.save(auditoria);
    }
}