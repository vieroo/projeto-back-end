package com.raizesdonordeste.api.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record ItemPedidoRequest(
        @NotNull(message = "Produto é obrigatório")
        Long produtoId,

        @Min(
                value = 1,
                message = "Quantidade deve ser maior que zero"
        )
        Integer quantidade
) {
}
