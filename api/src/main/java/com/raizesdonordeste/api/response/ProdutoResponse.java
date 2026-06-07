package com.raizesdonordeste.api.response;

import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;

@Schema(description = "Dados de retorno do produto")
public record ProdutoResponse(
        @Schema(
                description = "Identificador do produto",
                example = "1"
        )
        Long id,

        @Schema(
                description = "Nome do produto",
                example = "Cuscuz Nordestino"
        )
        String nome,

        @Schema(
                description = "Preço do produto",
                example = "19.90"
        )
        BigDecimal preco
) {
}
