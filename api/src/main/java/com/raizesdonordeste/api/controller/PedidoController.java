package com.raizesdonordeste.api.controller;

import com.raizesdonordeste.api.request.AtualizarStatusPedidoRequest;
import com.raizesdonordeste.api.request.PedidoRequest;
import com.raizesdonordeste.api.response.PedidoResponse;
import com.raizesdonordeste.application.service.PedidoService;
import com.raizesdonordeste.domain.enums.CanalPedido;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pedidos")
@RequiredArgsConstructor

public class PedidoController {

    private final PedidoService pedidoService;

    @PostMapping
    @PreAuthorize("hasRole('CLIENTE')")
    public ResponseEntity<PedidoResponse> criar(
            @RequestBody PedidoRequest request
    ) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(pedidoService.criar(request));
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN','ATENDENTE')")
    public ResponseEntity<List<PedidoResponse>> listar(
            @RequestParam(required = false)
            CanalPedido canalPedido
    ) {

        if (canalPedido != null) {
            return ResponseEntity.ok(
                    pedidoService.buscarPorCanal(canalPedido)
            );
        }

        return ResponseEntity.ok(
                pedidoService.listar()
        );
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','ATENDENTE')")
    public ResponseEntity<PedidoResponse> buscarPorId(
            @PathVariable Long id
    ) {

        return ResponseEntity.ok(
                pedidoService.buscarPorId(id)
        );
    }

    @PatchMapping("/{id}/status")
    @PreAuthorize("hasAnyRole('ADMIN','ATENDENTE')")
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

    @PatchMapping("/{id}/cancelar")
    @PreAuthorize("hasRole('CLIENTE')")
    public ResponseEntity<PedidoResponse> cancelar(
            @PathVariable Long id
    ) {

        return ResponseEntity.ok(
                pedidoService.cancelarPedido(id)
        );
    }
}
