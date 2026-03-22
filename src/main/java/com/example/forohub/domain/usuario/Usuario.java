package com.example.forohub.domain.usuario;

import com.example.forohub.domain.usuario.dto_usuario.UsuarioData;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Table(
        name = "usuarios",
        uniqueConstraints = @UniqueConstraint(columnNames = {"correo_electronico", "contrasena"})
)
@Entity(name = "Usuario")

@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    @Column(name = "correo_electronico")
    private String correoElectronico;
    private String contrasena;

    public Usuario(UsuarioData json) {
        this.nombre = json.nombre();
    }

}
