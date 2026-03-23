package com.example.forohub.domain.respuesta.dto_respuesta;

import com.example.forohub.domain.topico.dto_topico.TopicoData;
import com.example.forohub.domain.usuario.dto_usuario.UsuarioData;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record RespuestaData(
        @NotBlank
        String mensaje,
        @NotNull @Valid
        TopicoData topico,
        @NotNull @Valid
        UsuarioData autor
) {}
