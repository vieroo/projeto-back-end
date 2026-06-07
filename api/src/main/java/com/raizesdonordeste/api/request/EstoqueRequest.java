package com.raizesdonordeste.api.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record EstoqueRequest(
        @NotNull(message = "Produto é obrigatório")
        Long produtoId,

        @NotNull(message = "Unidade é obrigatória")
        Long unidadeId,

        @Min(
                value = 0,
                message = "Quantidade não pode ser negativa"
        )
        Integer quantidade
) {
}
