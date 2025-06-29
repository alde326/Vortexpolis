package com.vortexpolis.controller;

import com.vortexpolis.model.Cine;
import com.vortexpolis.service.CineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cines")
public class CineController {

    @Autowired
    private CineService cineService;

    @GetMapping
    public List<Cine> obtenerTodos() {
        return cineService.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cine> obtenerPorId(@PathVariable Long id) {
        Cine cine = cineService.obtenerPorId(id);
        if (cine != null) {
            return ResponseEntity.ok(cine);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public Cine crear(@RequestBody Cine cine) {
        return cineService.guardar(cine);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cine> actualizar(@PathVariable Long id, @RequestBody Cine cine) {
        // Validar si el cine existe
        Cine cineExistente = cineService.obtenerPorId(id);
        if (cineExistente == null) {
            return ResponseEntity.notFound().build();
        }

        // Actualizar los campos
        cine.setId(id); // Asegurarse de que el ID sea el correcto
        Cine cineActualizado = cineService.actualizar(cine);
        return ResponseEntity.ok(cineActualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        Cine cine = cineService.obtenerPorId(id);
        if (cine != null) {
            cineService.eliminar(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
