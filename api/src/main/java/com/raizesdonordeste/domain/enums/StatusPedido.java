package com.raizesdonordeste.domain.enums;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(
        description = "Status do pedido"
)
public enum StatusPedido {
    AGUARDANDO_PAGAMENTO,
    CANCELADO,
    EM_PREPARO,
    ENTREGUE,
    PAGO,
    PRONTO
}