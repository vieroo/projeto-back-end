package com.raizesdonordeste.api.request;

import com.raizesdonordeste.domain.enums.StatusPedido;

public record AtualizarStatusPedidoRequest(
        StatusPedido status
) {
}
