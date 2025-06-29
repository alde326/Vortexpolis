package com.vortexpolis.controller;

import com.vortexpolis.dto.PeliculaDTO;
import com.vortexpolis.mapper.PeliculaMapper;
import com.vortexpolis.model.Pelicula;
import com.vortexpolis.service.PeliculaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/peliculas")
public class PeliculaController {

    @Autowired
    private PeliculaService peliculaService;

    @Autowired
    private PeliculaMapper peliculaMapper;

    @GetMapping
    public List<PeliculaDTO> listarPeliculas() {
        List<Pelicula> peliculas = peliculaService.listarPeliculas();
        return peliculaMapper.toDTOList(peliculas);
    }

    @PostMapping
    public PeliculaDTO crearPelicula(@RequestBody PeliculaDTO peliculaDTO) {
        Pelicula pelicula = peliculaMapper.toEntity(peliculaDTO);
        Pelicula peliculaGuardada = peliculaService.crearPelicula(pelicula);
        return peliculaMapper.toDTO(peliculaGuardada);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PeliculaDTO> obtenerPeliculaPorId(@PathVariable Long id) {
        return peliculaService.obtenerPeliculaPorId(id)
                .map(pelicula -> ResponseEntity.ok(peliculaMapper.toDTO(pelicula)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<PeliculaDTO> actualizarPelicula(@PathVariable Long id, @RequestBody PeliculaDTO peliculaDTO) {
        try {
            Pelicula peliculaActualizada = peliculaService.actualizarPelicula(id, peliculaMapper.toEntity(peliculaDTO));
            return ResponseEntity.ok(peliculaMapper.toDTO(peliculaActualizada));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> inhabilitarPelicula(@PathVariable Long id) {
        try {
            peliculaService.inhabilitarPelicula(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
