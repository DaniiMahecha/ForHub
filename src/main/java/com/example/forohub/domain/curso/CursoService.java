package com.example.forohub.domain.curso;

import com.example.forohub.domain.curso.dto_curso.CursoData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class CursoService {

    @Autowired
    private CursoRepository repository;
    @Transactional

    public Curso save(CursoData json){
        var curso = new Curso(json);
        return  repository.save(curso);
    }

}
