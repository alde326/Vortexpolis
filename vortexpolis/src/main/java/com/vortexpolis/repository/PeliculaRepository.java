package com.vortexpolis.repository;

import com.vortexpolis.model.Pelicula;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

@Repository
public interface PeliculaRepository extends JpaRepository<Pelicula, Long> {

    // Consulta para traer todas las películas activas
    List<Pelicula> findByEstadoTrue();

    @Query("SELECT p FROM Pelicula p " +
           "LEFT JOIN FETCH p.funciones f " +
           "LEFT JOIN FETCH f.pelicula " +
           "WHERE p.id = :id")
    Optional<Pelicula> findByIdWithFunciones(@Param("id") Long id);
}

