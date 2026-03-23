package com.example.forohub.controllers;

import com.example.forohub.domain.topico.dto_topico.TopicoData;
import com.example.forohub.domain.topico.TopicoService;
import com.example.forohub.domain.topico.dto_topico.TopicoDTO;
import com.example.forohub.domain.topico.dto_topico.TopicoDetallado;
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
    public ResponseEntity<TopicoDetallado> listTopicoIdYActivo(@PathVariable Long idTopico) {

        var topicoDetallado = topicoService.listTopicoId( idTopico);
        return ResponseEntity.ok(topicoDetallado);

    }

    /*
    El método PUT, permite modificar un tópico ya existente, indicando en el endpoint el id del tópico a modificar y
    en el body la información a cambiar.

    Del Tópico solo se puede módificar el TITULO, MENSAJE y STATUS
    */
    @PutMapping("/{idTopico}")
    public ResponseEntity<TopicoDTO> modifyTopico(@PathVariable Long idTopico, @RequestBody TopicoModificado json) {
        var topicoModificado = topicoService.modifyTopico(idTopico, json);
        return ResponseEntity.ok(topicoModificado);
    }

    /*Método DELETE, no elimina la instancia de tópico de la base de datos, hace un DELETE lógico.*/
    @DeleteMapping("/{idTopico}")
    public ResponseEntity<Void> deleteTopico(@PathVariable Long idTopico) {
        topicoService.deleteTopico(idTopico);
        return ResponseEntity.noContent().build();
    }



}
