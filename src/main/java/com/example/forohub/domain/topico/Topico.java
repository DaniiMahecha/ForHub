package com.example.forohub.domain.topico;

import com.example.forohub.domain.topico.dto_topico.TopicoData;
import com.example.forohub.domain.topico.dto_topico.TopicoModificado;
import com.example.forohub.domain.usuario.Usuario;
import com.example.forohub.domain.curso.Curso;
import com.example.forohub.domain.respuesta.Respuesta;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Table(
        name = "topicos" ,
        uniqueConstraints = @UniqueConstraint(columnNames = {"titulo", "mensaje"})
        )
@Entity(name = "Topico")

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Topico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;
    private String mensaje;
    private LocalDateTime fechaCreacion;
    private Boolean activo;

    @Enumerated(EnumType.STRING)
    private StatusTopico status;

    @ManyToOne(optional = false)
    @JoinColumn(name = "autor_id", nullable = false) //Foreign Key
    private Usuario autor;

    @ManyToOne(optional = false)
    @JoinColumn(name = "curso_id", nullable = false) //Foreign Key
    private Curso curso;

    @OneToMany(mappedBy ="topico", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Respuesta> respuestas = new ArrayList<>();

    public Topico(TopicoData data, Usuario autor, Curso curso) {
        this.titulo = data.titulo();
        this.mensaje = data.mensaje();
        this.fechaCreacion = LocalDateTime.now();
        this.activo = true;
        this.status = StatusTopico.CREADO;
        this.autor = autor;
        this.curso = curso;
    }

    /*Método de dominio para manejar la relación*/
    public void addRespuesta(Respuesta respuesta) {
        respuestas.add(respuesta);
        respuesta.setTopico(this);
    }
}
