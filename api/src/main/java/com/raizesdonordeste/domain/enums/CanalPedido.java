package com.raizesdonordeste.domain.enums;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(
        description = "Canal pelo qual o pedido foi realizado"
)
public enum CanalPedido {
    APP,
    BALCAO,
    TOTEM,
    WEB
}