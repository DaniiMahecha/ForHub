package com.example.forohub.domain.topico;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.net.ContentHandler;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

public interface TopicoRepository extends JpaRepository<Topico, Long> {
    /*Uso @EntityGraph cuando voy a acceder a relaciones LAZY en esa consulta específica y quiero evitar el N+1 query problem.*/
    @EntityGraph(attributePaths = {"autor", "curso"})
    Page<Topico> findAllByActivoTrue(Pageable pageable);

    @EntityGraph(attributePaths = {"autor", "curso"})
    @Query("""
            SELECT t
            FROM Topico t
            WHERE t.activo = true
            AND t.curso.id = :id
            """)
    Page<Topico> findTopicoByCourseId(Long id,
                                           Pageable pageable);

    @EntityGraph(attributePaths = {"autor", "curso"})
    Optional<Topico> findByIdAndActivoTrue(Long id);
}
