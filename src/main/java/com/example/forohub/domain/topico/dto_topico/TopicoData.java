package com.example.forohub.domain.topico.dto_topico;


import com.example.forohub.domain.curso.dto_curso.CursoData;
import com.example.forohub.domain.usuario.dto_usuario.UsuarioData;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record TopicoData(
        @NotBlank
        String titulo,
        @NotBlank
        String mensaje,
        @NotNull @Valid
        UsuarioData autor,
        @NotNull @Valid
        CursoData curso
) {}
