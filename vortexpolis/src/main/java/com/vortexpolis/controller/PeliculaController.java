package com.vortexpolis.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vortexpolis.dto.PeliculaDTO;
import com.vortexpolis.mapper.PeliculaMapper;
import com.vortexpolis.model.Pelicula;
import com.vortexpolis.service.PeliculaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.security.core.Authentication;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/peliculas")
public class PeliculaController {

    @Autowired
    private PeliculaService peliculaService;

    @Autowired
    private PeliculaMapper peliculaMapper;

    @Autowired
    private ObjectMapper objectMapper; // Aquí inyectamos Jackson

    // Listar todas las películas
    @GetMapping
    public List<PeliculaDTO> listarPeliculas(Authentication authentication) {
        System.out.println("📌 Usuario autenticado: " + (authentication != null ? authentication.getName() : "No autenticado"));
        List<Pelicula> peliculas = peliculaService.listarPeliculas();
        return peliculaMapper.toDTOList(peliculas);
    }

    // Crear película con imagen
    @PostMapping
    public ResponseEntity<PeliculaDTO> crearPelicula(
            @RequestPart("pelicula") String peliculaJson,
            @RequestPart("imagen") MultipartFile imagen) {

        try {
            System.out.println("JSON recibido: " + peliculaJson);
            System.out.println("Archivo recibido: " + imagen.getOriginalFilename());

            // Usamos Jackson
            PeliculaDTO peliculaDTO = objectMapper.readValue(peliculaJson, PeliculaDTO.class);

            System.out.println("DTO deserializado: " + peliculaDTO.getTitulo());

            Pelicula pelicula = peliculaMapper.toEntity(peliculaDTO);
            Pelicula peliculaGuardada = peliculaService.crearPelicula(pelicula, imagen);

            return ResponseEntity.ok(peliculaMapper.toDTO(peliculaGuardada));
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(500).build();
        } catch (Exception e) {
            e.printStackTrace(); // Captura cualquier error inesperado
            return ResponseEntity.status(500).build();
        }
    }

    // Obtener película por ID
    @GetMapping("/{id}")
    public ResponseEntity<PeliculaDTO> obtenerPeliculaPorId(@PathVariable Long id) {
        return peliculaService.obtenerPeliculaPorId(id)
                .map(pelicula -> ResponseEntity.ok(peliculaMapper.toDTO(pelicula)))
                .orElse(ResponseEntity.notFound().build());
    }

    // Actualizar película sin imagen (si quieres actualizar la imagen, necesitarías otro endpoint similar al POST)
    @PutMapping("/{id}")
    public ResponseEntity<PeliculaDTO> actualizarPelicula(@PathVariable Long id, @RequestBody PeliculaDTO peliculaDTO) {
        try {
            Pelicula peliculaActualizada = peliculaService.actualizarPelicula(id, peliculaMapper.toEntity(peliculaDTO));
            return ResponseEntity.ok(peliculaMapper.toDTO(peliculaActualizada));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Deshabilitar película
    @PutMapping("/{id}/deshabilitar")
    public ResponseEntity<Void> deshabilitarPelicula(@PathVariable Long id) {
        try {
            peliculaService.inhabilitarPelicula(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Actualizar película CON imagen
    @PutMapping("/{id}/con-imagen")
    public ResponseEntity<PeliculaDTO> actualizarPeliculaConImagen(
            @PathVariable Long id,
            @RequestPart("pelicula") String peliculaJson,
            @RequestPart("imagen") MultipartFile imagen) {

        try {
            System.out.println("JSON recibido para actualizar: " + peliculaJson);
            System.out.println("Archivo recibido: " + imagen.getOriginalFilename());

            PeliculaDTO peliculaDTO = objectMapper.readValue(peliculaJson, PeliculaDTO.class);
            Pelicula pelicula = peliculaMapper.toEntity(peliculaDTO);

            Pelicula peliculaActualizada = peliculaService.actualizarPeliculaConImagen(id, pelicula, imagen);

            return ResponseEntity.ok(peliculaMapper.toDTO(peliculaActualizada));
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(500).build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).build();
        }
    }
}
