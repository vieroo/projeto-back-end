package com.raizesdonordeste.api.controller;

import com.raizesdonordeste.api.request.LoginRequest;
import com.raizesdonordeste.api.request.RegistroUsuarioRequest;
import com.raizesdonordeste.api.response.AuthResponse;
import com.raizesdonordeste.application.service.AuthService;
import com.raizesdonordeste.infraestructure.exception.ErrorResponse;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Tag(
        name = "Autenticação",
        description = "Registro e login de usuários"
)
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "Usuário registrado com sucesso"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "E-mail já cadastrado",
                    content = @Content(
                            schema = @Schema(
                                    implementation = ErrorResponse.class
                            )
                    )
            )
    })
    public ResponseEntity<Void> registrar (
            @Valid @RequestBody RegistroUsuarioRequest request
    ) {
        authService.registrar(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .build();
    }

    @PostMapping("/login")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Login realizado com sucesso"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "E-mail ou senha inválidos",
                    content = @Content(
                            schema = @Schema(
                                    implementation = ErrorResponse.class
                            )
                    )
            )
    })
    public ResponseEntity<AuthResponse> login (
            @Valid @RequestBody LoginRequest request
    ) {

        return ResponseEntity.ok(
                authService.login(request)
        );
    }
}
