package com.example.forohub.domain.usuario;

import com.example.forohub.domain.topico.Topico;
import com.example.forohub.domain.topico.TopicoData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository repository;

    @Transactional
    public Usuario save(UsuarioData json){
        var usuario = new Usuario(json);
        repository.save(usuario);
        return usuario;
    }
}
