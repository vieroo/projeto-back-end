package com.raizesdonordeste.api.response;

import com.raizesdonordeste.domain.enums.CanalPedido;
import com.raizesdonordeste.domain.enums.StatusPedido;
import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;
import java.util.List;

@Schema(description = "Dados do pedido")
public record PedidoResponse(
        @Schema(
                description = "Identificador do pedido",
                example = "10"
        )
        Long id,

        @Schema(
                description = "Status atual do pedido",
                example = "PAGO"
        )
        StatusPedido status,

        @Schema(
                description = "Canal utilizado para realizar o pedido",
                example = "APP"
        )
        CanalPedido canalPedido,

        @Schema(
                description = "Valor total do pedido",
                example = "85.50"
        )
        BigDecimal total,

        List<ItemPedidoResponse> itens
) {
}
