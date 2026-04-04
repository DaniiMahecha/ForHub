package com.example.forohub.controllers;

import com.example.forohub.domain.respuesta.dto_respuesta.RespuestaDTO;
import com.example.forohub.domain.respuesta.dto_respuesta.RespuestaData;
import com.example.forohub.domain.respuesta.RespuestaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/respuesta")
public class RespuestaController {

    @Autowired
    private RespuestaService respuestaService;

    @PostMapping("/topico/{idTopicoAResponder}")
    public ResponseEntity<?> createRespueta(@PathVariable Long idTopicoAResponder, @RequestBody RespuestaData json, UriComponentsBuilder ucBuilder) {
        var respuesta = respuestaService.save(idTopicoAResponder, json);
        var uri = ucBuilder
                .path("/respuesta/{id}")
                .buildAndExpand(respuesta.getId())
                .toUri();
        return ResponseEntity.created(uri).body(new RespuestaDTO(respuesta));
    }

}
