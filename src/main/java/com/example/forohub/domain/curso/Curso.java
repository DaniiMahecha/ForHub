package com.example.forohub.domain.curso;

import com.example.forohub.domain.curso.dto_curso.CursoData;
import com.example.forohub.domain.topico.Topico;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Table(
        name = "cursos",
        uniqueConstraints = @UniqueConstraint(columnNames = {"nombre"})
)
@Entity(name = "Curso")

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")

public class Curso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    @Enumerated(EnumType.STRING)
    private Categoria categoria;

    @OneToMany(mappedBy = "curso",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Topico> topico;

    private Boolean activo;

    public Curso(CursoData json) {
        this.id = null;
        this.nombre = json.nombre();
        this.categoria = Categoria.categoria(json.categoria());
        this.activo = true;
    }
}
