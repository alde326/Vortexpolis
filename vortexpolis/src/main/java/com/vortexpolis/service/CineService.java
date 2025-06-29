package com.vortexpolis.service;

import com.vortexpolis.model.Cine;
import com.vortexpolis.repository.CineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CineService {

    @Autowired
    private CineRepository cineRepository;

    public Cine guardar(Cine cine) {
        return cineRepository.save(cine);
    }

    public List<Cine> listarTodos() {
        return cineRepository.findAll();
    }

    public Cine obtenerPorId(Long id) {
        return cineRepository.findById(id).orElse(null);
    }

    public Cine actualizar(Cine cine) {
        return cineRepository.save(cine);
    }

    public void eliminar(Long id) {
        cineRepository.deleteById(id);
    }
}
