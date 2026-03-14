package com.example.forohub.Controllers;


import com.example.forohub.domain.usuario.UsuarioData;
import com.example.forohub.domain.usuario.UsuarioService;
import com.example.forohub.domain.usuario.dto_usuario.UsuarioDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService service;

    @PostMapping
    public ResponseEntity<UsuarioDTO> createTopico(@RequestBody UsuarioData json, UriComponentsBuilder ucBuilder) {
        var usuario = service.save(json);
        var uri = ucBuilder
                .path("/usuarios/{id}")
                .buildAndExpand(usuario.getId())
                .toUri();
        return ResponseEntity.created(uri).body(new UsuarioDTO(usuario));
    }
}
