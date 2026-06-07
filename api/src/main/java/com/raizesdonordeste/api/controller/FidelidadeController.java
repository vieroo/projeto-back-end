package com.raizesdonordeste.api.controller;

import com.raizesdonordeste.api.response.FidelidadeResponse;
import com.raizesdonordeste.application.service.FidelidadeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/fidelidades")
@RequiredArgsConstructor
public class FidelidadeController {

    private final FidelidadeService fidelidadeService;

    @GetMapping("/{clienteId}")
    @PreAuthorize("hasAnyRole('ADMIN','ATENDENTE')")
    public ResponseEntity<FidelidadeResponse> buscarPorCliente(
            @PathVariable Long clienteId
    ) {

        return ResponseEntity.ok(
                fidelidadeService.buscarPorCliente(clienteId)
        );
    }

    @GetMapping("/meus-pontos")
    @PreAuthorize("hasRole('CLIENTE')")
    public ResponseEntity<FidelidadeResponse> meusPontos() {

        return ResponseEntity.ok(
                fidelidadeService.meusPontos()
        );
    }
}