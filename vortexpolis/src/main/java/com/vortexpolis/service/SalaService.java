package com.vortexpolis.service;

import com.vortexpolis.model.Sala;
import com.vortexpolis.repository.SalaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SalaService {

    @Autowired
    private SalaRepository salaRepository;

    public Sala guardar(Sala sala) {
        return salaRepository.save(sala);
    }

    public List<Sala> listarTodas() {
        return salaRepository.findAll();
    }

    public Sala obtenerPorId(Long id) {
        return salaRepository.findById(id).orElse(null);
    }

    public Sala actualizar(Sala sala) {
        return salaRepository.save(sala);
    }

    public void eliminar(Long id) {
        salaRepository.deleteById(id);
    }
}
