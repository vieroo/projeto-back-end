package com.raizesdonordeste.api.response;

public record FidelidadeResponse(
        Long clienteId,
        String nomeCliente,
        Integer pontos
) {
}
