package com.vortexpolis.controller;

import com.vortexpolis.model.Compra;
import com.vortexpolis.service.CompraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/compras")
public class CompraController {

    @Autowired
    private CompraService compraService;

    @PostMapping
    public Compra registrarCompra(@RequestBody Compra compra) {
        return compraService.registrarCompra(compra);
    }

    @GetMapping
    public List<Compra> listarTodasLasCompras() {
        return compraService.listarTodasLasCompras();
    }

    @GetMapping("/cliente/{clienteId}")
    public ResponseEntity<List<Compra>> consultarComprasPorCliente(@PathVariable Long clienteId) {
        List<Compra> compras = compraService.consultarComprasPorCliente(clienteId);
        if (compras.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(compras);
        }
    }
}
