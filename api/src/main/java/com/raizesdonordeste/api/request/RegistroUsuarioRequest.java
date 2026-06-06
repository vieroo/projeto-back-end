package com.raizesdonordeste.api.request;

public record RegistroUsuarioRequest (
        String nome,
        String email,
        String senha
) {
}
