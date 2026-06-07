package com.raizesdonordeste.api.response;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Item do pedido")
public record ItemPedidoResponse(
        @Schema(
                description = "ID do produto",
                example = "3"
        )
        Long produtoId,

        @Schema(
                description = "Nome do produto",
                example = "Baião de Dois"
        )
        String produto,

        @Schema(
                description = "Quantidade solicitada",
                example = "2"
        )
        Integer quantidade
) {
}
