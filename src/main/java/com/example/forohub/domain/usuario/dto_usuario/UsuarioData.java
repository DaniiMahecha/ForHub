package com.example.forohub.domain.usuario.dto_usuario;

import jakarta.validation.constraints.NotBlank;

public record UsuarioData(
        @NotBlank
        String nombre
) {
}
