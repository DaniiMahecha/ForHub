package com.example.forohub.domain.usuario.dto_usuario;

import com.example.forohub.domain.usuario.Usuario;

public record UsuarioDTO(
        Long id,
        String nombre
) {

    public UsuarioDTO(Usuario usuario){
        this(usuario.getId(), usuario.getNombre());
    }

}
