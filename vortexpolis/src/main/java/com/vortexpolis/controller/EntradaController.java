package com.vortexpolis.controller;

import com.vortexpolis.model.Entrada;
import com.vortexpolis.service.EntradaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/entradas")
public class EntradaController {

    @Autowired
    private EntradaService entradaService;

    @GetMapping
    public List<Entrada> obtenerTodas() {
        return entradaService.listarTodas();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Entrada> obtenerPorId(@PathVariable Long id) {
        Entrada entrada = entradaService.obtenerPorId(id);
        if (entrada != null) {
            return ResponseEntity.ok(entrada);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public Entrada crear(@RequestBody Entrada entrada) {
        return entradaService.guardar(entrada);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Entrada> actualizar(@PathVariable Long id, @RequestBody Entrada entrada) {
        Entrada entradaExistente = entradaService.obtenerPorId(id);
        if (entradaExistente == null) {
            return ResponseEntity.notFound().build();
        }

        entrada.setId(id); // Asegurarse de que el ID sea correcto
        Entrada entradaActualizada = entradaService.actualizar(entrada);
        return ResponseEntity.ok(entradaActualizada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        Entrada entrada = entradaService.obtenerPorId(id);
        if (entrada != null) {
            entradaService.eliminar(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
