package com.example.forohub.domain.topico;

import com.example.forohub.domain.curso.Curso;
import com.example.forohub.domain.curso.CursoRepository;
import com.example.forohub.domain.usuario.Usuario;
import com.example.forohub.domain.usuario.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class TopicoService {

    @Autowired
    private TopicoRepository repository;
    @Autowired
    private CursoRepository cursoRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Transactional
    public Topico save(TopicoData json){

        Usuario autor = usuarioRepository
                .findByNombre(json.autor().nombre())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        Curso curso = cursoRepository
                .findByNombre(json.curso().nombre())
                .orElseThrow(() -> new RuntimeException("Curso no encontrado"));

        var topico = new Topico(json, autor, curso);


        repository.save(topico);

        return topico;
    }
}
