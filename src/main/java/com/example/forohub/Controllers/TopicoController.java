package com.example.forohub.Controllers;

import com.example.forohub.domain.topico.TopicoData;
import com.example.forohub.domain.topico.TopicoService;
import com.example.forohub.domain.topico.dto_topico.TopicoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/topicos")
public class TopicoController {

    @Autowired
    private TopicoService TopicoService;


    @PostMapping
    public ResponseEntity<TopicoDTO> createTopico(@RequestBody TopicoData json, UriComponentsBuilder ucBuilder) {
        var topico = TopicoService.save(json);
        var uri = ucBuilder
                .path("/topicos/{id}")
                .buildAndExpand(topico.getId())
                .toUri();
        return ResponseEntity.created(uri).body(new TopicoDTO(topico));
    }
}
