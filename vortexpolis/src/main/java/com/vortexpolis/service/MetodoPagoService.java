package com.vortexpolis.service;

import com.vortexpolis.model.MetodoPago;
import com.vortexpolis.repository.MetodoPagoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MetodoPagoService {

    @Autowired
    private MetodoPagoRepository metodoPagoRepository;

    public MetodoPago guardar(MetodoPago metodoPago) {
        return metodoPagoRepository.save(metodoPago);
    }

    public List<MetodoPago> listarTodos() {
        return metodoPagoRepository.findAll();
    }

    public MetodoPago obtenerPorId(Long id) {
        return metodoPagoRepository.findById(id).orElse(null);
    }

    public MetodoPago actualizar(MetodoPago metodoPago) {
        return metodoPagoRepository.save(metodoPago);
    }

    public void eliminar(Long id) {
        metodoPagoRepository.deleteById(id);
    }
}
