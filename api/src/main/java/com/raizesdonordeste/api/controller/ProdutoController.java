package com.raizesdonordeste.api.controller;

import com.raizesdonordeste.api.request.ProdutoRequest;
import com.raizesdonordeste.api.response.ProdutoResponse;
import com.raizesdonordeste.application.service.ProdutoService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/produtos")
@RequiredArgsConstructor
@Tag(
        name = "Produtos",
        description = "Operações relacionadas aos produtos"
)

public class ProdutoController {

    private final ProdutoService produtoService;

    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN','ATENDENTE')")
    public ResponseEntity<ProdutoResponse> criar(
           @Valid @RequestBody ProdutoRequest request
    ){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(produtoService.criar(request));
    }

    @GetMapping
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<List<ProdutoResponse>> listar() {
        return ResponseEntity.ok(
                produtoService.listar()
        );
    }

    @GetMapping("/{id}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<ProdutoResponse> buscarPorId(
            @PathVariable Long id
    ){
        return ResponseEntity.ok(
                produtoService.buscarPorId(id)
        );
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','ATENDENTE')")
    public ResponseEntity<ProdutoResponse> atualizar(
            @PathVariable Long id,
            @Valid @RequestBody ProdutoRequest request
    ){
        return ResponseEntity.ok(
                produtoService.atualizar(id, request)
        );
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deletar(
            @PathVariable Long id
    ){
        produtoService.deletar(id);

        return ResponseEntity.noContent().build();
    }
}
