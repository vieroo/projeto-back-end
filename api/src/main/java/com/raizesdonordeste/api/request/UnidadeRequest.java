package com.raizesdonordeste.api.request;

import jakarta.validation.constraints.NotBlank;

public record UnidadeRequest(
        @NotBlank(message = "Nome da unidade é obrigatório")
        String nome
) {
}
