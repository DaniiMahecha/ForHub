package com.example.forohub.domain.topico.dto_topico;

import com.example.forohub.domain.curso.dto_curso.CursoData;
import com.example.forohub.domain.usuario.dto_usuario.UsuarioData;

/*
No tengo que validar absolutamente nada, dado que modificar un topico, no implica modificar cada parte de él
*/
public record TopicoModificado(
    String titulo,
    String mensaje,
    String status
) {
}
