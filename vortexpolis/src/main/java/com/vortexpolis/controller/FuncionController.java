package com.vortexpolis.controller;

import com.vortexpolis.model.Funcion;
import com.vortexpolis.service.FuncionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/funciones")
public class FuncionController {

    @Autowired
    private FuncionService funcionService;

    @PostMapping
    public Funcion crearFuncion(@RequestBody Funcion funcion) {
        return funcionService.crearFuncion(funcion);
    }

    @GetMapping("/pelicula/{peliculaId}")
    public ResponseEntity<List<Funcion>> listarFuncionesPorPelicula(@PathVariable Long peliculaId) {
        List<Funcion> funciones = funcionService.listarFuncionesPorPelicula(peliculaId);
        if (funciones.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(funciones);
        }
    }
}
