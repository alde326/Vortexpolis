package com.vortexpolis.controller;

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

    @PostMapping
    public MetodoPago crearMetodoPago(@RequestBody MetodoPago metodoPago) {
        return metodoPagoService.guardar(metodoPago);
    }

    @GetMapping
    public List<MetodoPago> listarMetodosPago() {
        return metodoPagoService.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<MetodoPago> obtenerMetodoPagoPorId(@PathVariable Long id) {
        MetodoPago metodoPago = metodoPagoService.obtenerPorId(id);
        if (metodoPago != null) {
            return ResponseEntity.ok(metodoPago);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<MetodoPago> actualizarMetodoPago(@PathVariable Long id, @RequestBody MetodoPago metodoPago) {
        MetodoPago metodoExistente = metodoPagoService.obtenerPorId(id);
        if (metodoExistente != null) {
            metodoPago.setId(id);
            return ResponseEntity.ok(metodoPagoService.actualizar(metodoPago));
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
