package com.raizesdonordeste.api.request;

public record ItemPedidoRequest(
        Long produtoId,
        Integer quantidade
) {
}
