package com.raizesdonordeste.api.response;

import com.raizesdonordeste.domain.enums.CanalPedido;
import com.raizesdonordeste.domain.enums.StatusPedido;

import java.math.BigDecimal;
import java.util.List;

public record PedidoResponse(
        Long id,
        StatusPedido status,
        CanalPedido canalPedido,
        BigDecimal total,
        List<ItemPedidoResponse> itens
) {
}
