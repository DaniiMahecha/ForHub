package com.example.forohub.controllers;

import com.example.forohub.domain.topico.dto_topico.TopicoData;
import com.example.forohub.domain.topico.TopicoService;
import com.example.forohub.domain.topico.dto_topico.TopicoDTO;
import com.example.forohub.domain.topico.dto_topico.TopicoModificado;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/topicos")
public class TopicoController {

    /*
    Este Controller se encarga de conectar con el repositorio de los tópicos y sus servicios.
    Mapea las request de cada usuario e implementa un CRUD Básico, siguiendo buenas prácticas de programación
    devolviendo un código HTTP, paginación en los Getter y administra el intercambio de información mediante DTOs
    */
    @Autowired
    private TopicoService topicoService;

    /*
    En este método 'createTopico', se persiste un topico, su lógica se maneja en el service y retorna una response
    en un Body la entidad persistida, el código HTTP correspondiente y su ubicación.
    */
    @PostMapping
    public ResponseEntity<TopicoDTO> createTopico(@RequestBody @Valid TopicoData json, UriComponentsBuilder ucBuilder) {
        var topico = topicoService.save(json);
        var uri = ucBuilder
                .path("/topicos/{id}")
                .buildAndExpand(topico.getId())
                .toUri();
        return ResponseEntity.created(uri).body(new TopicoDTO(topico));
    }

    /*
    Existen dos métodos GET. Para listar todos los tópicos activos
    Y listar un tópico específico, por su ID
    */
    @GetMapping
    public ResponseEntity<Page<TopicoDTO>> listTopico(
            @PageableDefault(size = 10, sort={"fechaCreacion"}, direction = Sort.Direction.ASC) Pageable  pageable) {

        var page = topicoService.listTopico(pageable);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{idTopico}")
    public ResponseEntity<Page<TopicoDTO>> listTopicoIdYActivo(@PathVariable Long idTopico,
            @PageableDefault(size = 10, sort={"fechaCreacion"}, direction = Sort.Direction.ASC) Pageable pageable) {

        var page = topicoService.listTopicoCourseId( idTopico, pageable);
        return ResponseEntity.ok(page);

    }

    @PutMapping("/{idTopico}")
    public ResponseEntity<TopicoDTO> modifyTopico(@PathVariable Long idTopico, @RequestBody TopicoModificado json) {
        var topicoModificado = topicoService.modifyTopico(idTopico, json);
        return ResponseEntity.ok(topicoModificado);
    }


}
