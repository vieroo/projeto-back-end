package com.raizesdonordeste.api.response;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Informações de fidelidade do cliente")
public record FidelidadeResponse(
        @Schema(
                description = "ID do cliente",
                example = "5"
        )
        Long clienteId,

        @Schema(
                description = "Nome do cliente",
                example = "Joao Silva"
        )
        String nomeCliente,

        @Schema(
                description = "Quantidade de pontos acumulados",
                example = "120"
        )
        Integer pontos
) {
}
