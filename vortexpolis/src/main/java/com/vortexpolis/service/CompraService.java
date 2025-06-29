package com.vortexpolis.service;

import com.vortexpolis.model.Compra;
import com.vortexpolis.repository.CompraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompraService {

    @Autowired
    private CompraRepository compraRepository;

    public Compra registrarCompra(Compra compra) {
        return compraRepository.save(compra);
    }

    public List<Compra> consultarComprasPorCliente(Long clienteId) {
        return compraRepository.findByClienteId(clienteId);
    }

    public List<Compra> listarTodasLasCompras() {
        return compraRepository.findAll();
    }
}
