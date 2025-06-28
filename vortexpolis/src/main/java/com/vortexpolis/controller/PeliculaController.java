package com.vortexpolis.controller;

import com.vortexpolis.dto.PeliculaDTO;
import com.vortexpolis.service.PeliculaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/peliculas")
@Validated
public class PeliculaController {

    @Autowired
    private PeliculaService peliculaService;

    @PostMapping
    public ResponseEntity<PeliculaDTO> crear(@RequestBody @Validated PeliculaDTO peliculaDTO) {
        return ResponseEntity.ok(peliculaService.crearPelicula(peliculaDTO));
    }

    @GetMapping
    public ResponseEntity<List<PeliculaDTO>> listar() {
        return ResponseEntity.ok(peliculaService.listarPeliculas());
    }

    @PutMapping("/{id}")
    public ResponseEntity<PeliculaDTO> actualizar(@PathVariable Long id, @RequestBody @Validated PeliculaDTO peliculaDTO) {
        return ResponseEntity.ok(peliculaService.actualizarPelicula(id, peliculaDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> inhabilitar(@PathVariable Long id) {
        peliculaService.inhabilitarPelicula(id);
        return ResponseEntity.noContent().build();
    }
}
