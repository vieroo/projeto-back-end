package com.raizesdonordeste.api.response;

public record EstoqueResponse(
        Long id,
        Long produtoId,
        String produtoNome,
        Long unidadeId,
        String unidadeNome,
        Integer quantidade
) {
}
