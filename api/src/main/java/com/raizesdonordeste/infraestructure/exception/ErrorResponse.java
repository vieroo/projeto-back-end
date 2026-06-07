package com.raizesdonordeste.infraestructure.exception;

import java.time.LocalDateTime;

public record ErrorResponse(
        LocalDateTime timestamp,
        Integer status,
        String message
) {
}
