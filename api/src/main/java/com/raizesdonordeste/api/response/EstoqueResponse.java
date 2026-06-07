package com.raizesdonordeste.api.response;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Informações de estoque")
public record EstoqueResponse(
        @Schema(
                description = "ID do registro de estoque",
                example = "1"
        )
        Long id,

        @Schema(
                description = "ID do produto"
        )
        Long produtoId,

        @Schema(
                description = "Nome do produto"
        )
        String produtoNome,

        @Schema(
                description = "ID da unidade"
        )
        Long unidadeId,

        @Schema(
                description = "Nome da unidade"
        )
        String unidadeNome,

        @Schema(
                description = "Quantidade disponível",
                example = "25"
        )
        Integer quantidade
) {
}
