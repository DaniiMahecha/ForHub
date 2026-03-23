package com.example.forohub.domain.topico.dto_topico;

import com.example.forohub.domain.curso.dto_curso.CursoDTO;
import com.example.forohub.domain.respuesta.dto_respuesta.RespuestaDTO;
import com.example.forohub.domain.topico.StatusTopico;
import com.example.forohub.domain.topico.Topico;
import com.example.forohub.domain.usuario.dto_usuario.UsuarioDTO;

import java.time.LocalDateTime;
import java.util.List;

public record TopicoDetallado(
        Long id,
        String titulo,
        String mensaje,
        LocalDateTime fechaCreacion,
        StatusTopico status,
        UsuarioDTO autor,
        CursoDTO curso,
        List<RespuestaDTO> respuestas
) {
    public TopicoDetallado(Topico topico) {
        this(
                topico.getId(),
                topico.getTitulo(),
                topico.getMensaje(),
                topico.getFechaCreacion(),
                topico.getStatus(),
                new UsuarioDTO(topico.getAutor()),
                new CursoDTO(topico.getCurso()),
                topico.getRespuestas().stream()
                        .filter(r -> r.getActivo() == true)
                        .map(RespuestaDTO::new)
                        .toList()
        );
    }
}
