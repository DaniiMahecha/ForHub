package com.example.forohub.domain.respuesta;


import com.example.forohub.domain.respuesta.dto_respuesta.RespuestaData;
import com.example.forohub.domain.topico.Topico;
import com.example.forohub.domain.topico.TopicoRepository;
import com.example.forohub.domain.usuario.Usuario;
import com.example.forohub.domain.usuario.UsuarioRepository;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RespuestaService {

    @Autowired
    private RespuestaRepository respuestaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private TopicoRepository topicoRepository;

    @Transactional //POST
    public Respuesta save(Long idTopicoAResponder, RespuestaData json) {
        Topico topico = topicoRepository.findByIdAndActivoTrue(idTopicoAResponder)
                .orElseThrow(() -> new ValidationException("Tópico no encontrado"));

        Usuario autor = usuarioRepository.findByNombre(json.autor().nombre())
                .orElseThrow(() -> new ValidationException("Usuario no encontrado"));

        return  respuestaRepository.save(new Respuesta(json, topico, autor));

    }
}
