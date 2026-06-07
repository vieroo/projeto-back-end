package com.raizesdonordeste.api.controller;

import com.raizesdonordeste.domain.entity.Auditoria;
import com.raizesdonordeste.domain.repository.AuditoriaRepository;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/auditorias")
@RequiredArgsConstructor
@Tag(
        name = "Auditoria"
)
public class AuditoriaController {

    private final AuditoriaRepository auditoriaRepository;

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public List<Auditoria> listar() {
        return auditoriaRepository.findAll();
    }
}