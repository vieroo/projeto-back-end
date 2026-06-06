package com.raizesdonordeste.api.controller;

import com.raizesdonordeste.api.request.AtualizarStatusPedidoRequest;
import com.raizesdonordeste.api.request.PedidoRequest;
import com.raizesdonordeste.api.response.PedidoResponse;
import com.raizesdonordeste.application.service.PedidoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pedidos")
@RequiredArgsConstructor

public class PedidoController {

    private final PedidoService pedidoService;

    @PostMapping
    public ResponseEntity<PedidoResponse> criar(
            @RequestBody PedidoRequest request
    ) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(pedidoService.criar(request));
    }

    @GetMapping
    public ResponseEntity<List<PedidoResponse>> listar() {

        return ResponseEntity.ok(
                pedidoService.listar()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<PedidoResponse> buscarPorId(
            @PathVariable Long id
    ) {

        return ResponseEntity.ok(
                pedidoService.buscarPorId(id)
        );
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<PedidoResponse> atualizarStatus(
            @PathVariable Long id,
            @RequestBody AtualizarStatusPedidoRequest request
    ) {

        return ResponseEntity.ok(
                pedidoService.atualizarStatus(
                        id,
                        request.status()
                )
        );
    }
}
