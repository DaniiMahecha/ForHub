package com.example.forohub.domain.topico;

import com.example.forohub.domain.usuario.Usuario;
import com.example.forohub.domain.curso.Curso;
import com.example.forohub.domain.respuesta.Respuesta;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Table(name = "topicos")
@Entity(name = "Topico")

@Getter
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

    @ManyToOne
    @JoinColumn(name = "autor_id") //Foreign Key
    private Usuario autor;

    @ManyToOne
    @JoinColumn(name = "curso_id") //Foreign Key
    private Curso curso;

    @OneToMany(mappedBy ="topico", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Respuesta> respuestas;

}
