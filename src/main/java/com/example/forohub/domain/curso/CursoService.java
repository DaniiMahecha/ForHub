package com.example.forohub.domain.curso;

import com.example.forohub.domain.curso.dto_curso.CursoDTO;
import com.example.forohub.domain.curso.dto_curso.CursoData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class CursoService {

    @Autowired
    private CursoRepository repository;

    //POST
    @Transactional
    public Curso save(CursoData json){
        var curso = new Curso(json);
        return  repository.save(curso);
    }
    //GET
    public Page<CursoDTO> getCursos(Pageable pageable) {
        return repository.findAllByActivoTrue(pageable).map(CursoDTO::new);
    }

    //DELETE
    @Transactional
    public void deleteCurso(Long id) {
        var curso = repository.getReferenceById(id);
        curso.setActivo(false);
    }
}
