package com.vortexpolis.repository;

import com.vortexpolis.model.Funcion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FuncionRepository extends JpaRepository<Funcion, Long> {
    List<Funcion> findByPeliculaIdAndEstadoTrue(Long peliculaId);
}
