package com.raizesdonordeste.application.service;

import com.raizesdonordeste.api.request.LoginRequest;
import com.raizesdonordeste.api.request.RegistroUsuarioRequest;
import com.raizesdonordeste.api.response.AuthResponse;
import com.raizesdonordeste.domain.entity.Usuario;
import com.raizesdonordeste.domain.enums.Role;
import com.raizesdonordeste.domain.repository.UsuarioRepository;
import com.raizesdonordeste.infraestructure.config.BusinessException;
import com.raizesdonordeste.infraestructure.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    private final JwtService jwtService;

    public void registrar(
            RegistroUsuarioRequest request
    ) {
        Optional<Usuario> usuarioComEmail = usuarioRepository.findByEmail(request.email());

        if (usuarioRepository.findByEmail(request.email()).isPresent()) {
            throw new BusinessException(
                    "E-mail ja cadastrado"
            );
        }

        Usuario usuario = Usuario.builder()
                .nome(request.nome())
                .email(request.email())
                .senha(passwordEncoder.encode(
                        request.senha()
                ))
                .role(Role.CLIENTE)
                .build();

        usuarioRepository.save(usuario);
    }

    public AuthResponse login(
            LoginRequest request
    ) {
        Usuario usuario = usuarioRepository
                .findByEmail(request.email())
                .orElseThrow();

        boolean senhaValida =
                passwordEncoder.matches(
                        request.senha(),
                        usuario.getSenha()
                );

        if (!senhaValida) {
            throw new BusinessException("E-mail ou senha inválido.");
        }

        String token =
                jwtService.gerarToken(usuario.getEmail());

        return  new AuthResponse(token);
    }
}
