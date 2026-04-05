package com.example.forohub.controllers;

import com.example.forohub.domain.curso.dto_curso.CursoData;
import com.example.forohub.domain.curso.CursoService;
import com.example.forohub.domain.curso.dto_curso.CursoDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/cursos")
public class CursoController {

    @Autowired
    private CursoService service;

    @PostMapping
    public ResponseEntity<CursoDTO> createCurso(@RequestBody @Valid CursoData json, UriComponentsBuilder ucBuilder) {
        var curso = service.save(json);
        var uri = ucBuilder
                .path("/cursos/{id}")
                .buildAndExpand(curso.getId())
                .toUri();
        return ResponseEntity.created(uri).body(new CursoDTO(curso));
    }

    @GetMapping
    public ResponseEntity<Page<CursoDTO>> getCursos(Pageable pageable) {
        var page = service.getCursos(pageable);
        return ResponseEntity.ok(page);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCurso(@PathVariable Long id) {
        service.deleteCurso(id);
        return ResponseEntity.noContent().build();
    }
}
