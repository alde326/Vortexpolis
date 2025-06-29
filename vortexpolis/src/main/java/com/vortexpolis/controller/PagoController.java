package com.vortexpolis.controller;

import com.vortexpolis.model.Pago;
import com.vortexpolis.service.PagoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pagos")
public class PagoController {

    @Autowired
    private PagoService pagoService;

    @PostMapping
    public Pago crearPago(@RequestBody Pago pago) {
        return pagoService.guardar(pago);
    }

    @GetMapping
    public List<Pago> listarPagos() {
        return pagoService.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pago> obtenerPagoPorId(@PathVariable Long id) {
        Pago pago = pagoService.obtenerPorId(id);
        if (pago != null) {
            return ResponseEntity.ok(pago);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pago> actualizarPago(@PathVariable Long id, @RequestBody Pago pago) {
        Pago pagoExistente = pagoService.obtenerPorId(id);
        if (pagoExistente != null) {
            pago.setId(id);
            return ResponseEntity.ok(pagoService.actualizar(pago));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarPago(@PathVariable Long id) {
        Pago pagoExistente = pagoService.obtenerPorId(id);
        if (pagoExistente != null) {
            pagoService.eliminar(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
