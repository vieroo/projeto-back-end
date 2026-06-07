package com.raizesdonordeste.api.response;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Dados da unidade")
public record UnidadeResponse(
        @Schema(
                description = "Identificador da unidade",
                example = "1"
        )
        Long id,

        @Schema(
                description = "Nome da unidade",
                example = "Raízes do Nordeste - Centro"
        )
        String nome
) {
}
