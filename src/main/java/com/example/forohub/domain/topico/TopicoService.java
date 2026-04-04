package com.example.forohub.domain.topico;

import com.example.forohub.domain.curso.Curso;
import com.example.forohub.domain.curso.CursoRepository;
import com.example.forohub.domain.topico.dto_topico.TopicoDTO;
import com.example.forohub.domain.topico.dto_topico.TopicoData;
import com.example.forohub.domain.topico.dto_topico.TopicoDetallado;
import com.example.forohub.domain.topico.dto_topico.TopicoModificado;
import com.example.forohub.domain.usuario.Usuario;
import com.example.forohub.domain.usuario.UsuarioRepository;
import jakarta.validation.ValidationException;
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

    @Transactional //POST
    public Topico save(TopicoData json){

        Usuario autor = usuarioRepository
                .findByNombre(json.autor().nombre())
                .orElseThrow(() -> new ValidationException("Usuario no encontrado"));

        Curso curso = cursoRepository
                .findByNombre(json.curso().nombre())
                .orElseThrow(() -> new ValidationException("Curso no encontrado"));

        var topico = new Topico(json, autor, curso);

        return repository.save(topico);
    }

    //GET
    public Page<TopicoDTO> listTopico(Pageable pageable) {
        return repository.findAllByActivoTrue(pageable).map(TopicoDTO::new);
    }
    //GET
    public TopicoDetallado listTopicoId(Long id) {
        Topico topico = repository.findByIdAndActivoTrue(id)
                .orElseThrow(() -> new RuntimeException("Tópico no encontrado"));

        return new TopicoDetallado(topico);
    }

    @Transactional //PUT
    public TopicoDTO modifyTopico(Long idTopico, TopicoModificado json) {
        Topico topico = repository.findByIdAndActivoTrue(idTopico)
                .orElseThrow(() -> new RuntimeException("Topico no encontrado"));

        if (json.titulo() != null) {topico.setTitulo(json.titulo());}

        if (json.mensaje() != null) {topico.setMensaje(json.mensaje());}

        if (json.status() != null) {topico.setStatus(StatusTopico.statusTopico(json.status()));}

        return new TopicoDTO(topico);

    }

    @Transactional //DELETE
    public void deleteTopico(Long idTopico) {
        Topico topico = repository.findByIdAndActivoTrue(idTopico)
                .orElseThrow(() -> new RuntimeException("Topico no encontrado"));

        topico.deleteTopico();
    }
}
