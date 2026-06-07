package com.raizesdonordeste.api.controller;

import com.raizesdonordeste.api.response.FidelidadeResponse;
import com.raizesdonordeste.application.service.FidelidadeService;
import com.raizesdonordeste.infraestructure.exception.ErrorResponse;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/fidelidades")
@RequiredArgsConstructor
@Tag(
        name = "Fidelidade",
        description = "Programa de pontos dos clientes"
)

public class FidelidadeController {

    private final FidelidadeService fidelidadeService;

    @GetMapping("/{clienteId}")
    @PreAuthorize("hasAnyRole('ADMIN','ATENDENTE')")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Fidelidade encontrada"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Cliente ou fidelidade não encontrada",
                    content = @Content(
                            schema = @Schema(
                                    implementation = ErrorResponse.class
                            )
                    )
            )
    })
    public ResponseEntity<FidelidadeResponse> buscarPorCliente(
            @PathVariable Long clienteId
    ) {

        return ResponseEntity.ok(
                fidelidadeService.buscarPorCliente(clienteId)
        );
    }

    @GetMapping("/meus-pontos")
    @PreAuthorize("hasRole('CLIENTE')")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Pontos consultados com sucesso"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Cadastro de fidelidade não encontrado",
                    content = @Content(
                            schema = @Schema(
                                    implementation = ErrorResponse.class
                            )
                    )
            )
    })
    public ResponseEntity<FidelidadeResponse> meusPontos() {

        return ResponseEntity.ok(
                fidelidadeService.meusPontos()
        );
    }
}