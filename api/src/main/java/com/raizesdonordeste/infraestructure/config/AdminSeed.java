package com.raizesdonordeste.infraestructure.config;

import com.raizesdonordeste.domain.entity.Usuario;
import com.raizesdonordeste.domain.enums.Role;
import com.raizesdonordeste.domain.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AdminSeed implements CommandLineRunner {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) {

        if (usuarioRepository.existsByEmail("admin@raizes.com")) {
            return;
        }

        Usuario admin = Usuario.builder()
                .nome("Administrador")
                .email("admin@raizes.com")
                .senha(passwordEncoder.encode("admin123"))
                .role(Role.ADMIN)
                .build();

        usuarioRepository.save(admin);
    }
}
