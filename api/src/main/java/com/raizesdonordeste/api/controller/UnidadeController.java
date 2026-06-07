package com.raizesdonordeste.api.controller;

import com.raizesdonordeste.api.request.UnidadeRequest;
import com.raizesdonordeste.api.response.UnidadeResponse;
import com.raizesdonordeste.application.service.UnidadeService;
import com.raizesdonordeste.domain.entity.Unidade;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/unidades")
@RequiredArgsConstructor

public class UnidadeController {

    private final UnidadeService unidadeService;

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<UnidadeResponse> criar(
            @RequestBody UnidadeRequest request
    ) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(unidadeService.criar(request));
    }

    @GetMapping
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<List<UnidadeResponse>> listar() {

        return ResponseEntity.ok(
                unidadeService.listar()
        );
    }

    @GetMapping("/{id}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<UnidadeResponse> buscarPorId(
            @PathVariable Long id
    ) {

        return ResponseEntity.ok(
                unidadeService.buscarPorId(id)
        );
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<UnidadeResponse> atualizar(
            @PathVariable Long id,
            @RequestBody UnidadeRequest request
    ) {

        return ResponseEntity.ok(
                unidadeService.atualizar(id, request)
        );
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deletar(
            @PathVariable Long id
    ) {

        unidadeService.deletar(id);

        return ResponseEntity.noContent().build();
    }
}
