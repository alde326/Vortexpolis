package com.vortexpolis.controller;

import com.vortexpolis.dto.CompraDTO;
import com.vortexpolis.mapper.CompraMapper;
import com.vortexpolis.model.Compra;
import com.vortexpolis.service.CompraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("/api/compras")
public class CompraController {

    @Autowired
    private CompraService compraService;

    @Autowired
    private CompraMapper compraMapper;

    // Registrar compra (recibe y devuelve DTO)
    @PostMapping
    public ResponseEntity<CompraDTO> registrarCompra(@Valid @RequestBody CompraDTO compraDTO) {
        Compra compra = compraMapper.toEntity(compraDTO);
        Compra nuevaCompra = compraService.registrarCompra(compra);
        CompraDTO nuevaCompraDTO = compraMapper.toDTO(nuevaCompra);
        return ResponseEntity.ok(nuevaCompraDTO);
    }

    // Listar todas las compras (devuelve DTOs)
    @GetMapping
    public ResponseEntity<List<CompraDTO>> listarTodasLasCompras() {
        List<Compra> compras = compraService.listarTodasLasCompras();
        List<CompraDTO> comprasDTO = compraMapper.toDTOList(compras);
        return ResponseEntity.ok(comprasDTO);
    }

    // Consultar compras por cliente (devuelve DTOs)
    @GetMapping("/cliente/{clienteId}")
    public ResponseEntity<List<CompraDTO>> consultarComprasPorCliente(@PathVariable Long clienteId) {
        List<Compra> compras = compraService.consultarComprasPorCliente(clienteId);
        if (compras.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            List<CompraDTO> comprasDTO = compraMapper.toDTOList(compras);
            return ResponseEntity.ok(comprasDTO);
        }
    }
}
