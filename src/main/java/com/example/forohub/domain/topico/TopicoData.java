package com.example.forohub.domain.topico;


import com.example.forohub.domain.curso.CursoData;
import com.example.forohub.domain.usuario.UsuarioData;
import com.fasterxml.jackson.annotation.JsonAlias;
import jakarta.validation.Valid;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;

public record TopicoData(
        @NotBlank
        String titulo,
        @NotBlank
        String mensaje,
        @NotNull @Valid
        UsuarioData autor,
        @NotNull @Valid
        CursoData curso

) {
}
