package com.example.forohub.domain.usuario;

import jakarta.validation.constraints.NotBlank;

public record UsuarioData(
        @NotBlank
        String nombre
) {
}
