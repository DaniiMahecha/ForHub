package com.example.forohub.controllers;


import com.example.forohub.domain.usuario.dto_usuario.UsuarioData;
import com.example.forohub.domain.usuario.UsuarioService;
import com.example.forohub.domain.usuario.dto_usuario.UsuarioDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService service;

    @PostMapping
    public ResponseEntity<UsuarioDTO> createUsuario(@RequestBody @Valid UsuarioData json, UriComponentsBuilder ucBuilder) {
        var usuario = service.save(json);
        var uri = ucBuilder
                .path("/usuarios/{id}")
                .buildAndExpand(usuario.getId())
                .toUri();
        return ResponseEntity.created(uri).body(new UsuarioDTO(usuario));
    }

    @GetMapping
    public ResponseEntity<Page<UsuarioDTO>> getUsuarios(Pageable pageable) {
        var page =  service.getUsuarios(pageable);
        return ResponseEntity.ok(page);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUsuario(@PathVariable Long id) {
        service.deleteUsuario(id);
        return ResponseEntity.noContent().build();
    }
}
