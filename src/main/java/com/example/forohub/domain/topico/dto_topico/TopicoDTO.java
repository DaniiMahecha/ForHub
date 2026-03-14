package com.example.forohub.domain.topico.dto_topico;

import com.example.forohub.domain.curso.Curso;
import com.example.forohub.domain.curso.dto_curso.CursoDTO;
import com.example.forohub.domain.topico.StatusTopico;
import com.example.forohub.domain.topico.Topico;
import com.example.forohub.domain.usuario.Usuario;
import com.example.forohub.domain.usuario.dto_usuario.UsuarioDTO;

import java.time.LocalDateTime;

public record TopicoDTO(
        Long id,
        String titulo,
        String mensaje,
        LocalDateTime fechaCreacion,
        StatusTopico status,
        UsuarioDTO autor,
        CursoDTO curso

) {

    public TopicoDTO(Topico topico){
        this(
                topico.getId(),
                topico.getTitulo(),
                topico.getMensaje(),
                topico.getFechaCreacion(),
                topico.getStatus(),
                new UsuarioDTO(topico.getAutor()),
                new CursoDTO(topico.getCurso())
        );
    }
}
