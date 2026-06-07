package com.raizesdonordeste.api.response;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Resposta de autenticação")
public record AuthResponse(
        @Schema(
                description = "Token JWT para autenticação",
                example = "eyJhbGciOiJIUzI1NiJ9..."
        )
        String token
) {
}
