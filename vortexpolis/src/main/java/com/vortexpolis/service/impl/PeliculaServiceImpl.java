package com.vortexpolis.service.impl;

import com.vortexpolis.dto.PeliculaDTO;
import com.vortexpolis.mapper.PeliculaMapper;
import com.vortexpolis.model.Pelicula;
import com.vortexpolis.repository.PeliculaRepository;
import com.vortexpolis.service.PeliculaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PeliculaServiceImpl implements PeliculaService {

    @Autowired
    private PeliculaRepository peliculaRepository;

    @Autowired
    private PeliculaMapper peliculaMapper;

    @Override
    public PeliculaDTO crearPelicula(PeliculaDTO peliculaDTO) {
        Pelicula pelicula = peliculaMapper.toEntity(peliculaDTO);
        return peliculaMapper.toDto(peliculaRepository.save(pelicula));
    }

    @Override
    public List<PeliculaDTO> listarPeliculas() {
        return peliculaMapper.toDtoList(peliculaRepository.findByActivaTrue());
    }

    @Override
    public PeliculaDTO actualizarPelicula(Long id, PeliculaDTO peliculaDTO) {
        Pelicula pelicula = peliculaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Película no encontrada"));

        pelicula.setTitulo(peliculaDTO.getTitulo());
        pelicula.setDescripcion(peliculaDTO.getDescripcion());
        pelicula.setImagenUrl(peliculaDTO.getImagenUrl());

        return peliculaMapper.toDto(peliculaRepository.save(pelicula));
    }

    @Override
    public void inhabilitarPelicula(Long id) {
        Pelicula pelicula = peliculaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Película no encontrada"));
        pelicula.setActiva(false);
        peliculaRepository.save(pelicula);
    }
}
