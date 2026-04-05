package com.example.forohub.domain.respuesta;


import com.example.forohub.domain.respuesta.dto_respuesta.RespuestaDTO;
import com.example.forohub.domain.respuesta.dto_respuesta.RespuestaData;
import com.example.forohub.domain.topico.Topico;
import com.example.forohub.domain.topico.TopicoRepository;
import com.example.forohub.domain.usuario.Usuario;
import com.example.forohub.domain.usuario.UsuarioRepository;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
                .orElseThrow(() -> new ValidationException("No existe un Tópico registrado con el id: " + idTopicoAResponder));

        Usuario autor = usuarioRepository.findByNombre(json.autor().nombre())
                .orElseThrow(() -> new ValidationException("No existe un autor registrado con el nombre informado: " + json.autor().nombre()));

        return  respuestaRepository.save(new Respuesta(json, topico, autor));

    }

    //GET
    public List<RespuestaDTO> getRespuestas(Long idTopico) {
        Topico topico = topicoRepository.findByIdAndActivoTrue(idTopico)
                .orElseThrow(() -> new ValidationException("No existe un Tópico registrado con el id: " + idTopico));
        return topico.getRespuestas().stream().map(RespuestaDTO::new).toList();
    }

    //DELETE
    @Transactional
    public void deleteRespuesta(Long idRespuesta) {
        Respuesta respuesta = respuestaRepository.findByIdAndActivoTrue(idRespuesta)
                .orElseThrow(() -> new ValidationException("No existe una Respuesta registrado con el id: " + idRespuesta));
        respuesta.setActivo(false);
    }
}
