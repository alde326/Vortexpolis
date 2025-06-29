package com.vortexpolis.controller;

import com.vortexpolis.dto.MetodoPagoDTO;
import com.vortexpolis.mapper.MetodoPagoMapper;
import com.vortexpolis.model.MetodoPago;
import com.vortexpolis.service.MetodoPagoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/metodos-pago")
public class MetodoPagoController {

    @Autowired
    private MetodoPagoService metodoPagoService;

    @Autowired
    private MetodoPagoMapper metodoPagoMapper;

    @PostMapping
    public MetodoPagoDTO crearMetodoPago(@RequestBody MetodoPagoDTO metodoPagoDTO) {
        MetodoPago metodoPago = metodoPagoMapper.toEntity(metodoPagoDTO);
        MetodoPago metodoGuardado = metodoPagoService.guardar(metodoPago);
        return metodoPagoMapper.toDTO(metodoGuardado);
    }

    @GetMapping
    public List<MetodoPagoDTO> listarMetodosPago() {
        List<MetodoPago> metodosPago = metodoPagoService.listarTodos();
        return metodoPagoMapper.toDTOList(metodosPago);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MetodoPagoDTO> obtenerMetodoPagoPorId(@PathVariable Long id) {
        MetodoPago metodoPago = metodoPagoService.obtenerPorId(id);
        if (metodoPago != null) {
            return ResponseEntity.ok(metodoPagoMapper.toDTO(metodoPago));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<MetodoPagoDTO> actualizarMetodoPago(@PathVariable Long id, @RequestBody MetodoPagoDTO metodoPagoDTO) {
        MetodoPago metodoExistente = metodoPagoService.obtenerPorId(id);
        if (metodoExistente != null) {
            MetodoPago metodoActualizado = metodoPagoMapper.toEntity(metodoPagoDTO);
            metodoActualizado.setId(id);
            MetodoPago metodoGuardado = metodoPagoService.actualizar(metodoActualizado);
            return ResponseEntity.ok(metodoPagoMapper.toDTO(metodoGuardado));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarMetodoPago(@PathVariable Long id) {
        MetodoPago metodoExistente = metodoPagoService.obtenerPorId(id);
        if (metodoExistente != null) {
            metodoPagoService.eliminar(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
