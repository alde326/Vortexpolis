package com.vortexpolis.service;

import com.vortexpolis.model.Entrada;
import com.vortexpolis.repository.EntradaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EntradaService {

    @Autowired
    private EntradaRepository entradaRepository;

    public Entrada guardar(Entrada entrada) {
        return entradaRepository.save(entrada);
    }

    public List<Entrada> listarTodas() {
        return entradaRepository.findAll();
    }

    public Entrada obtenerPorId(Long id) {
        return entradaRepository.findById(id).orElse(null);
    }

    public Entrada actualizar(Entrada entrada) {
        return entradaRepository.save(entrada);
    }

    public void eliminar(Long id) {
        entradaRepository.deleteById(id);
    }
}
