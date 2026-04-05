package com.example.forohub.domain.usuario;

import com.example.forohub.domain.perfil.Perfil;
import com.example.forohub.domain.usuario.dto_usuario.UsuarioData;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;


@Table(
        name = "usuarios",
        uniqueConstraints = @UniqueConstraint(columnNames = {"correo_electronico", "contrasena"})
)
@Entity(name = "Usuario")

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private Boolean activo;

    @Column(name = "correo_electronico")
    private String correoElectronico;
    private String contrasena;

    @ManyToMany(mappedBy = "usuarios")
    private List<Perfil> perfiles;

    public Usuario(UsuarioData json) {
        this.id = null;
        this.nombre = json.nombre();
        this.contrasena = json.password();
        this.correoElectronico = json.correo();
        this.activo = true;
    }
}
