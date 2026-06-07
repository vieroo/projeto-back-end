package com.raizesdonordeste.application.service;

import com.raizesdonordeste.api.response.FidelidadeResponse;
import com.raizesdonordeste.domain.entity.Fidelidade;
import com.raizesdonordeste.domain.entity.Usuario;
import com.raizesdonordeste.domain.repository.FidelidadeRepository;
import com.raizesdonordeste.domain.repository.UsuarioRepository;
import com.raizesdonordeste.infraestructure.exception.BusinessException;
import com.raizesdonordeste.infraestructure.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.security.core.Authentication;


@Service
@RequiredArgsConstructor
public class FidelidadeService {

    private final FidelidadeRepository fidelidadeRepository;
    private final UsuarioRepository usuarioRepository;

    public FidelidadeResponse buscarPorCliente(
            Long clienteId
    ) {

        Fidelidade fidelidade =
                fidelidadeRepository
                        .findByClienteId(clienteId)
                        .orElseThrow(() ->
                               new BusinessException(
                                        "Cliente sem cadastro de fidelidade"
                                ));

        return new FidelidadeResponse(
                fidelidade.getCliente().getId(),
                fidelidade.getCliente().getNome(),
                fidelidade.getPontos()
        );
    }

    public FidelidadeResponse meusPontos() {

        Authentication authentication =
                SecurityContextHolder
                        .getContext()
                        .getAuthentication();

        String email = authentication.getName();

        Usuario usuario = usuarioRepository
                .findByEmail(email)
                .orElseThrow(() ->
                       new ResourceNotFoundException("Usuário nao encontrado"));

        Fidelidade fidelidade =
                fidelidadeRepository
                        .findByClienteId(usuario.getId())
                        .orElseThrow(() ->
                               new BusinessException(
                                        "Cliente sem cadastro de fidelidade"
                                ));

        return new FidelidadeResponse(
                fidelidade.getCliente().getId(),
                fidelidade.getCliente().getNome(),
                fidelidade.getPontos()
        );
    }
}