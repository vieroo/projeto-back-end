package com.raizesdonordeste.api.controller;

import com.raizesdonordeste.api.request.EstoqueRequest;
import com.raizesdonordeste.api.response.EstoqueResponse;
import com.raizesdonordeste.application.service.EstoqueService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/estoques")
@RequiredArgsConstructor

public class EstoqueController {

    private final EstoqueService estoqueService;

    @PostMapping
    public ResponseEntity<EstoqueResponse> criar(
            @RequestBody EstoqueRequest request
    ) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(estoqueService.criar(request));
    }

    @GetMapping
    public ResponseEntity<List<EstoqueResponse>> listar() {

        return ResponseEntity.ok(
                estoqueService.listar()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<EstoqueResponse> buscarPorId(
            @PathVariable Long id
    ) {

        return ResponseEntity.ok(
                estoqueService.buscarPorId(id)
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<EstoqueResponse> atualizar(
            @PathVariable Long id,
            @RequestBody EstoqueRequest request
    ) {

        return ResponseEntity.ok(
                estoqueService.atualizar(id, request)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(
            @PathVariable Long id
    ) {

        estoqueService.deletar(id);

        return ResponseEntity.noContent().build();
    }
}
