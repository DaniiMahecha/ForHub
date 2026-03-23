package com.example.forohub.domain.topico;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface TopicoRepository extends JpaRepository<Topico, Long> {
    /*
    Uso @EntityGraph cuando voy a acceder a relaciones LAZY en esa consulta específica y quiero evitar el N+1 query problem
    y sé que existen relaciones entre entidades.
    */
    @EntityGraph(attributePaths = {"autor", "curso"})
    Page<Topico> findAllByActivoTrue(Pageable pageable);

    @EntityGraph(attributePaths = {"autor", "curso", "respuestas", "respuestas.autor"})
    Optional<Topico> findByIdAndActivoTrue(Long id);
}
