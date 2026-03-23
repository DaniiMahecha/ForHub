package com.example.forohub.domain.respuesta;

import com.example.forohub.domain.respuesta.dto_respuesta.RespuestaData;
import com.example.forohub.domain.usuario.Usuario;
import com.example.forohub.domain.topico.Topico;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Table(name = "respuestas")
@Entity(name = "Respuesta")

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Respuesta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String mensaje;

    @ManyToOne //Foreign Key
    @JoinColumn(name = "topico_id")
    private Topico topico;

    @ManyToOne //Foreign Key
    @JoinColumn(name = "autor_id")
    private Usuario autor;

    @Column(name = "fecha_creacion")
    private LocalDateTime fechaCreacion;


    private Boolean activo;


    public Respuesta(RespuestaData json, Topico topico, Usuario autor) {
        this.mensaje = json.mensaje();
        this.activo = true;
        this.fechaCreacion = LocalDateTime.now();
        this.topico = topico;
        this.autor = autor;

    }
}
