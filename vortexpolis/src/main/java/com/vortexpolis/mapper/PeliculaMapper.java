package com.vortexpolis.mapper;

import com.vortexpolis.dto.PeliculaDTO;
import com.vortexpolis.model.Pelicula;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PeliculaMapper {

    PeliculaDTO toDto(Pelicula pelicula);

    Pelicula toEntity(PeliculaDTO peliculaDTO);

    List<PeliculaDTO> toDtoList(List<Pelicula> peliculas);
}