package com.example.forohub.domain.curso;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CursoRepository extends JpaRepository<Curso, Long> {
    Optional<Curso> findByNombre(String nombre);

    //DListar todos los cursos activos de la base de datos
    Page<Curso> findAllByActivoTrue(Pageable pageable);
}
