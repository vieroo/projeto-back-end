package com.raizesdonordeste.api.controller;

import com.raizesdonordeste.api.request.AtualizarStatusPedidoRequest;
import com.raizesdonordeste.api.request.PedidoRequest;
import com.raizesdonordeste.api.response.PedidoResponse;
import com.raizesdonordeste.application.service.PedidoService;
import com.raizesdonordeste.domain.enums.CanalPedido;
import com.raizesdonordeste.infraestructure.exception.ErrorResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pedidos")
@RequiredArgsConstructor
@Tag(
        name = "Pedidos",
        description = "Gerenciamento de pedidos"
)

public class PedidoController {

    private final PedidoService pedidoService;

    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN','ATENDENTE', 'CLIENTE')")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "Pedido criado com sucesso"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Estoque insuficiente ou pagamento recusado",
                    content = @Content(
                            schema = @Schema(
                                    implementation = ErrorResponse.class
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Cliente, produto ou estoque não encontrado",
                    content = @Content(
                            schema = @Schema(
                                    implementation = ErrorResponse.class
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "Token inválido ou não informado"
            )
    })
    public ResponseEntity<PedidoResponse> criar(
           @Valid @RequestBody PedidoRequest request
    ) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(pedidoService.criar(request));
    }

    @PostMapping("/{pedidoId}/pagamento")
    @PreAuthorize("hasAnyRole('ADMIN', 'CLIENTE')")
    @Operation(summary = "Processar pagamento de um pedido")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Pagamento processado com sucesso"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Pedido não pode ser pago"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Pedido não encontrado"
            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "Token não informado ou inválido"
            )
    })
    public ResponseEntity<PedidoResponse> pagar(
            @PathVariable Long pedidoId
    ) {
        return ResponseEntity.ok(
                pedidoService.processarPagamento(pedidoId)
        );
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN','ATENDENTE')")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Pedidos encontrados"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Canal de pedido inválido",
                    content = @Content(
                            schema = @Schema(
                                    implementation = ErrorResponse.class
                            )
                    )
            )
    })
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
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Pedido encontrado"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Pedido não encontrado",
                    content = @Content(
                            schema = @Schema(
                                    implementation = ErrorResponse.class
                            )
                    )
            )
    })
    public ResponseEntity<PedidoResponse> buscarPorId(
            @PathVariable Long id
    ) {

        return ResponseEntity.ok(
                pedidoService.buscarPorId(id)
        );
    }

    @PatchMapping("/{id}/status")
    @PreAuthorize("hasAnyRole('ADMIN','ATENDENTE')")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Status atualizado com sucesso"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Status inválido para atualização",
                    content = @Content(
                            schema = @Schema(
                                    implementation = ErrorResponse.class
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Pedido não encontrado",
                    content = @Content(
                            schema = @Schema(
                                    implementation = ErrorResponse.class
                            )
                    )
            )
    })
    public ResponseEntity<PedidoResponse> atualizarStatus(
            @PathVariable Long id,
            @Valid @RequestBody AtualizarStatusPedidoRequest request
    ) {

        return ResponseEntity.ok(
                pedidoService.atualizarStatus(
                        id,
                        request.status()
                )
        );
    }

    @PatchMapping("/{id}/cancelar")
    @PreAuthorize("hasAnyRole('ADMIN','ATENDENTE', 'CLIENTE')")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Pedido cancelado com sucesso"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Pedido não pode ser cancelado",
                    content = @Content(
                            schema = @Schema(
                                    implementation = ErrorResponse.class
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Pedido não encontrado",
                    content = @Content(
                            schema = @Schema(
                                    implementation = ErrorResponse.class
                            )
                    )
            )
    })
    public ResponseEntity<PedidoResponse> cancelar(
            @PathVariable Long id
    ) {

        return ResponseEntity.ok(
                pedidoService.cancelarPedido(id)
        );
    }
}
