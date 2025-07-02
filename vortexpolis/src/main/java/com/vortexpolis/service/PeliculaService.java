package com.vortexpolis.service;

import com.vortexpolis.model.Pelicula;
import com.vortexpolis.repository.PeliculaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class PeliculaService {

    private final PeliculaRepository peliculaRepository;
    private final CloudinaryService cloudinaryService;

    @Autowired
    public PeliculaService(PeliculaRepository peliculaRepository, CloudinaryService cloudinaryService) {
        this.peliculaRepository = peliculaRepository;
        this.cloudinaryService = cloudinaryService;
    }

    // Listar todas las películas activas
    public List<Pelicula> listarPeliculas() {
        return peliculaRepository.findByEstadoTrue();
    }

    // Crear una nueva película con imagen
    public Pelicula crearPelicula(Pelicula pelicula, MultipartFile imagen) throws IOException {
        // Subir imagen a Cloudinary
        String imageUrl = cloudinaryService.uploadImage(imagen);

        // Asignar la URL de la imagen a la película
        pelicula.setImagenUrl(imageUrl);
        pelicula.setEstado(true); // Las películas nuevas están activas por defecto

        return peliculaRepository.save(pelicula);
    }

    // Consultar una película por ID
    public Optional<Pelicula> obtenerPeliculaPorId(Long id) {
        return peliculaRepository.findById(id);
    }

    // Actualizar una película
    public Pelicula actualizarPelicula(Long id, Pelicula peliculaActualizada) {
        Pelicula peliculaExistente = peliculaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Película no encontrada con ID: " + id));

        peliculaExistente.setTitulo(peliculaActualizada.getTitulo());
        peliculaExistente.setDescripcion(peliculaActualizada.getDescripcion());
        peliculaExistente.setImagenUrl(peliculaActualizada.getImagenUrl());

        return peliculaRepository.save(peliculaExistente);
    }

    public Pelicula obtenerPeliculaConFunciones(Long id) {
        return peliculaRepository.findByIdWithFunciones(id)
                .orElseThrow(() -> new RuntimeException("Película no encontrada con ID: " + id));
    }

    // Inhabilitar una película (Soft delete)
    public void inhabilitarPelicula(Long id) {
        Pelicula peliculaExistente = peliculaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Película no encontrada con ID: " + id));

        peliculaExistente.setEstado(false);
        peliculaRepository.save(peliculaExistente);
    }

    // Actualizar película con nueva imagen
    public Pelicula actualizarPeliculaConImagen(Long id, Pelicula peliculaActualizada, MultipartFile imagen) throws IOException {
        Pelicula peliculaExistente = peliculaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Película no encontrada con ID: " + id));

        // Actualizar campos de texto
        peliculaExistente.setTitulo(peliculaActualizada.getTitulo());
        peliculaExistente.setDescripcion(peliculaActualizada.getDescripcion());
        peliculaExistente.setEstado(peliculaActualizada.getEstado());

        // Subir la nueva imagen a Cloudinary
        String nuevaImagenUrl = cloudinaryService.uploadImage(imagen);
        peliculaExistente.setImagenUrl(nuevaImagenUrl);

        return peliculaRepository.save(peliculaExistente);
    }
}
