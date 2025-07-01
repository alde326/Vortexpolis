package com.vortexpolis.service;

import com.vortexpolis.dto.CompraRequestDTO;
import com.vortexpolis.model.*;
import com.vortexpolis.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CompraService {

    @Autowired
    private CompraRepository compraRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private FuncionRepository funcionRepository;

    @Autowired
    private MetodoPagoRepository metodoPagoRepository;

    @Autowired
    private EntradaRepository entradaRepository;

    @Autowired
    private PagoRepository pagoRepository;

    @Transactional
    public Compra registrarCompra(CompraRequestDTO compraRequestDTO) {

        // Buscar Cliente
        Cliente cliente = clienteRepository.findById(compraRequestDTO.getClienteId())
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));

        // Buscar Función
        Funcion funcion = funcionRepository.findById(compraRequestDTO.getFuncionId())
                .orElseThrow(() -> new RuntimeException("Función no encontrada"));

        // Calcular total
        double precioUnitario = funcion.getPrecioEntrada();
        double total = precioUnitario * compraRequestDTO.getCantidad();

        // Crear Compra
        Compra compra = new Compra();
        compra.setCliente(cliente);
        compra.setFecha(LocalDateTime.now());
        compra.setEstado("COMPLETADA");
        compra.setTotal(total);
        compra = compraRepository.save(compra);

        // Crear Entradas
        for (int i = 0; i < compraRequestDTO.getCantidad(); i++) {
            Entrada entrada = new Entrada();
            entrada.setCompra(compra);
            entrada.setFuncion(funcion);
            entrada.setPelicula(funcion.getPelicula());
            entrada.setPrecioUnitario(precioUnitario);
            entrada.setEstado("ACTIVA");
            entradaRepository.save(entrada);
        }

        // Crear Pago
        MetodoPago metodoPago = metodoPagoRepository.findById(compraRequestDTO.getMetodoPagoId())
                .orElseThrow(() -> new RuntimeException("Método de pago no encontrado"));

        Pago pago = new Pago();
        pago.setCompra(compra);
        pago.setMetodoPago(metodoPago);
        pago.setFechaPago(LocalDateTime.now());
        pago.setMonto(total);
        pagoRepository.save(pago);

        return compra;
    }

    public List<Compra> consultarComprasPorCliente(Long clienteId) {
        return compraRepository.findByClienteId(clienteId);
    }

    public List<Compra> listarTodasLasCompras() {
        return compraRepository.findAll();
    }
}
