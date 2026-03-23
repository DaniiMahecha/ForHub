package com.example.forohub.domain.curso.dto_curso;

import jakarta.validation.constraints.NotBlank;

public record CursoData(
        @NotBlank
        String nombre,
        @NotBlank
        String categoria
) {}
