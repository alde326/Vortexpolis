package com.vortexpolis.mapper;

import com.vortexpolis.dto.FuncionDTO;
import com.vortexpolis.model.Funcion;
import com.vortexpolis.model.Pelicula;
import org.mapstruct.*;

import java.time.LocalDateTime;
import java.util.List;

@Mapper(componentModel = "spring")
public interface FuncionMapper {

    @Mappings({
        @Mapping(source = "pelicula.id", target = "peliculaId"),
        @Mapping(source = "sala", target = "salaId", qualifiedByName = "mapSalaStringToId"),
        @Mapping(target = "fechaHora", expression = "java(mapFechaHora(funcion.getFecha(), funcion.getHora()))"),
        @Mapping(source = "estado", target = "estado"),
        @Mapping(source = "precioEntrada", target = "precioEntrada")
    })
    FuncionDTO toDTO(Funcion funcion);

    @Mappings({
        @Mapping(target = "pelicula", expression = "java(mapPelicula(funcionDTO.getPeliculaId()))"),
        @Mapping(target = "fecha", expression = "java(mapFecha(funcionDTO.getFechaHora()))"),
        @Mapping(target = "hora", expression = "java(mapHora(funcionDTO.getFechaHora()))"),
        @Mapping(source = "salaId", target = "sala", qualifiedByName = "mapSalaIdToString"),
        @Mapping(target = "cine", ignore = true),
        @Mapping(target = "entradas", ignore = true),
        @Mapping(source = "precioEntrada", target = "precioEntrada")
    })
    Funcion toEntity(FuncionDTO funcionDTO);

    List<FuncionDTO> toDTOList(List<Funcion> funciones);
    List<Funcion> toEntityList(List<FuncionDTO> funcionDTOs);

    // Métodos auxiliares

    default LocalDateTime mapFechaHora(java.time.LocalDate fecha, java.time.LocalTime hora) {
        if (fecha == null || hora == null) {
            return null;
        }
        return LocalDateTime.of(fecha, hora);
    }

    default java.time.LocalDate mapFecha(LocalDateTime fechaHora) {
        if (fechaHora == null) {
            return null;
        }
        return fechaHora.toLocalDate();
    }

    default java.time.LocalTime mapHora(LocalDateTime fechaHora) {
        if (fechaHora == null) {
            return null;
        }
        return fechaHora.toLocalTime();
    }

    default Pelicula mapPelicula(Long id) {
        if (id == null) {
            return null;
        }
        Pelicula pelicula = new Pelicula();
        pelicula.setId(id);
        return pelicula;
    }

    @Named("mapSalaIdToString")
    default String mapSalaIdToString(Long salaId) {
        return salaId != null ? String.valueOf(salaId) : null;
    }

    @Named("mapSalaStringToId")
    default Long mapSalaStringToId(String sala) {
        try {
            return sala != null ? Long.parseLong(sala) : null;
        } catch (NumberFormatException e) {
            return null;
        }
    }
}
