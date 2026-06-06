package com.raizesdonordeste.api.request;

public record EstoqueRequest(
        Long produtoId,
        Long unidadeId,
        Integer quantidade
) {
}
