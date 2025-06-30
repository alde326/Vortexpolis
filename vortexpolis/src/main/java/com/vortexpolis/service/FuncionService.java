package com.vortexpolis.service;

import com.vortexpolis.dto.FuncionDTO;
import com.vortexpolis.mapper.FuncionMapper;
import com.vortexpolis.model.Funcion;
import com.vortexpolis.repository.FuncionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FuncionService {

    @Autowired
    private FuncionRepository funcionRepository;

    @Autowired
    private FuncionMapper funcionMapper;

    public Funcion crearFuncion(FuncionDTO funcionDTO) {
        Funcion funcion = funcionMapper.toEntity(funcionDTO);

        // Separar fecha y hora
        funcion.setFecha(funcionDTO.getFechaHora().toLocalDate());
        funcion.setHora(funcionDTO.getFechaHora().toLocalTime());

        return funcionRepository.save(funcion);
    }

    public List<Funcion> listarTodas() {
        return funcionRepository.findAll();
    }

    public List<Funcion> listarFuncionesPorPelicula(Long peliculaId) {
        return funcionRepository.findByPeliculaIdAndEstadoTrue(peliculaId);
    }
}

