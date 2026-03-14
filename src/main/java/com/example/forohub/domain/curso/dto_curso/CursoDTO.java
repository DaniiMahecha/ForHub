package com.example.forohub.domain.curso.dto_curso;

import com.example.forohub.domain.curso.Curso;

public record CursoDTO(
        Long id,
        String nombre,
        String categoria
) {
    public CursoDTO(Curso curso) {
        this(
                curso.getId(),
                curso.getNombre(),
                curso.getCategoria().name()
        );
    }
}
