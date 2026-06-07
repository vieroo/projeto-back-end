package com.raizesdonordeste.api.request;

import com.raizesdonordeste.domain.enums.CanalPedido;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record PedidoRequest(
        @NotNull(message = "Cliente é obrigatório")
        Long clientId,

        @NotNull(message = "Canal do pedido é obrigatório")
        CanalPedido canalPedido,

        @NotNull(message = "Unidade é obrigatória")
        Long unidadeId,

        @NotEmpty(message = "Pedido deve possuir itens")
        @Valid
        List<ItemPedidoRequest> itens
) {
}
