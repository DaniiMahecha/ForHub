package com.example.forohub.domain.curso;

import com.example.forohub.domain.topico.Topico;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Table(
        name = "cursos",
        uniqueConstraints = @UniqueConstraint(columnNames = {"nombre"})
)
@Entity(name = "Curso")

@Getter
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
