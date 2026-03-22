package com.example.forohub.domain.topico;

import com.example.forohub.domain.curso.Curso;
import com.example.forohub.domain.curso.CursoRepository;
import com.example.forohub.domain.topico.dto_topico.TopicoDTO;
import com.example.forohub.domain.topico.dto_topico.TopicoData;
import com.example.forohub.domain.topico.dto_topico.TopicoModificado;
import com.example.forohub.domain.usuario.Usuario;
import com.example.forohub.domain.usuario.UsuarioRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

        return repository.save(topico);
    }

    public Page<TopicoDTO> listTopico(Pageable pageable) {
        return repository.findAllByActivoTrue(pageable).map(TopicoDTO::new);
    }

    public Page<TopicoDTO> listTopicoCourseId(Long id, Pageable pageable) {
        return repository.findTopicoByCourseId(id, pageable).map(TopicoDTO::new);
    }

    @Transactional
    public TopicoDTO modifyTopico(Long idTopico, TopicoModificado json) {
        Topico topico = repository.findByIdAndActivoTrue(idTopico)
                .orElseThrow(() -> new RuntimeException("Topico no encontrado"));

        if (json.titulo() != null) {topico.setTitulo(json.titulo());}

        if (json.mensaje() != null) {topico.setMensaje(json.mensaje());}


        return new TopicoDTO(topico);

    }
}
