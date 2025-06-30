package com.vortexpolis.repository;

import com.vortexpolis.model.Funcion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FuncionRepository extends JpaRepository<Funcion, Long> {

    // Tu consulta ya existente
    List<Funcion> findByPeliculaIdAndEstadoTrue(Long peliculaId);

    // Nueva consulta con JOIN FETCH
    @Query("SELECT f FROM Funcion f JOIN FETCH f.pelicula p WHERE f.id = :id")
    Optional<Funcion> findByIdWithDetails(@Param("id") Long id);
}
