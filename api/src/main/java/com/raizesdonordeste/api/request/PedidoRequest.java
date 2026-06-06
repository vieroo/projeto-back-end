package com.raizesdonordeste.api.request;

import com.raizesdonordeste.domain.enums.CanalPedido;

import java.util.List;

public record PedidoRequest(
        Long clientId,
        CanalPedido canalPedido,
        Long unidadeId,
        List<ItemPedidoRequest> itens
) {
}
