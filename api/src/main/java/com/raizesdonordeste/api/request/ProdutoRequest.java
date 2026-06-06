package com.raizesdonordeste.api.request;

import java.math.BigDecimal;

public record ProdutoRequest(
        String nome,
        BigDecimal preco
) {
}
