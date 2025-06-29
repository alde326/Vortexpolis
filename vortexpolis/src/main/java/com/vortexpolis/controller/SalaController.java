package com.vortexpolis.controller;

import com.vortexpolis.model.Sala;
import com.vortexpolis.service.SalaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/salas")
public class SalaController {

    @Autowired
    private SalaService salaService;

    @GetMapping
    public List<Sala> listarSalas() {
        return salaService.listarTodas();
    }

    @PostMapping
    public Sala crearSala(@RequestBody Sala sala) {
        return salaService.guardar(sala);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Sala> obtenerSalaPorId(@PathVariable Long id) {
        Sala sala = salaService.obtenerPorId(id);
        if (sala != null) {
            return ResponseEntity.ok(sala);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Sala> actualizarSala(@PathVariable Long id, @RequestBody Sala sala) {
        Sala salaExistente = salaService.obtenerPorId(id);
        if (salaExistente != null) {
            sala.setId(id); // Asegurarse de actualizar la sala correcta
            Sala salaActualizada = salaService.actualizar(sala);
            return ResponseEntity.ok(salaActualizada);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarSala(@PathVariable Long id) {
        Sala sala = salaService.obtenerPorId(id);
        if (sala != null) {
            salaService.eliminar(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
