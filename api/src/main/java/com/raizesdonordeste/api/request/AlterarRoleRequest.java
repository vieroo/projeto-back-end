package com.raizesdonordeste.api.request;

import com.raizesdonordeste.domain.enums.Role;

public record AlterarRoleRequest(
        Role role
) {
}
