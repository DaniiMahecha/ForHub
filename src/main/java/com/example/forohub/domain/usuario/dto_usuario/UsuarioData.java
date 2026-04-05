package com.example.forohub.domain.usuario.dto_usuario;

import com.fasterxml.jackson.annotation.JsonAlias;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UsuarioData(
        @NotBlank
        String nombre,
        @NotBlank @Email @JsonAlias("correoElectronico")
        String correo,
        @NotBlank @JsonAlias("contrasena")
        String password
) {}
