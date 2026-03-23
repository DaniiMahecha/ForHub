package com.example.forohub.domain.respuesta.dto_respuesta;

import com.example.forohub.domain.respuesta.Respuesta;
import com.example.forohub.domain.usuario.dto_usuario.UsuarioDTO;
import java.time.LocalDateTime;

public record RespuestaDTO(
        Long id,
        String mensaje,
        UsuarioDTO autor,
        LocalDateTime fechaCreacion
) {
    public RespuestaDTO(Respuesta respuesta) {
        this(
                respuesta.getId(),
                respuesta.getMensaje(),
                new UsuarioDTO(respuesta.getAutor()),
                respuesta.getFechaCreacion()
        );
    }
}
