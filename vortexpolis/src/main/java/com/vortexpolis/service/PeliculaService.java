package com.vortexpolis.service;

import com.vortexpolis.dto.PeliculaDTO;

import java.util.List;

public interface PeliculaService {

    PeliculaDTO crearPelicula(PeliculaDTO peliculaDTO);

    List<PeliculaDTO> listarPeliculas();

    PeliculaDTO actualizarPelicula(Long id, PeliculaDTO peliculaDTO);

    void inhabilitarPelicula(Long id);
}