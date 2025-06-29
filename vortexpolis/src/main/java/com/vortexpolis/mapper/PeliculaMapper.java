package com.vortexpolis.mapper;

import com.vortexpolis.dto.PeliculaDTO;
import com.vortexpolis.model.Pelicula;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PeliculaMapper {

    PeliculaDTO toDTO(Pelicula pelicula);

    Pelicula toEntity(PeliculaDTO peliculaDTO);

    List<PeliculaDTO> toDTOList(List<Pelicula> peliculas);

    List<Pelicula> toEntityList(List<PeliculaDTO> peliculaDTOs);
}
