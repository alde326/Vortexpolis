package com.vortexpolis.service;

import com.vortexpolis.model.Funcion;
import com.vortexpolis.repository.FuncionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FuncionService {

    @Autowired
    private FuncionRepository funcionRepository;

    public Funcion crearFuncion(Funcion funcion) {
        return funcionRepository.save(funcion);
    }

    public List<Funcion> listarFuncionesPorPelicula(Long peliculaId) {
        return funcionRepository.findByPeliculaIdAndEstadoTrue(peliculaId);
    }
}
