package com.example.forohub.domain.perfil;

import com.example.forohub.domain.usuario.Usuario;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Table(name = "perfiles")
@Entity(name = "Perfil")

@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Perfil {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    @ManyToMany(targetEntity = Usuario.class ,fetch = FetchType.LAZY ,cascade = CascadeType.ALL)
    @JoinTable(
            name = "perfiles_usuarios", //Nombre de la tabla
            joinColumns = @JoinColumn(name = "perfil"), //Nombre perfil
            inverseJoinColumns = @JoinColumn(name = "usuario") //Nombre Usuario
    )
    private List<Usuario> usuarios;
}
