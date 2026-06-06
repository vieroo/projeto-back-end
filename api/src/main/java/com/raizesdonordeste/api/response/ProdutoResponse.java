package com.raizesdonordeste.api.response;

import java.math.BigDecimal;

public record ProdutoResponse(
        Long id,
        String nome,
        BigDecimal preco
) {
}
