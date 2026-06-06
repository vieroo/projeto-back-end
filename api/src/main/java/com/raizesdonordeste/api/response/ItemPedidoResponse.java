package com.raizesdonordeste.api.response;

public record ItemPedidoResponse(
        Long produtoId,
        String produto,
        Integer quantidade
) {
}
