package com.vortexpolis.mapper;

import com.vortexpolis.dto.FuncionDTO;
//import com.vortexpolis.model.Cine;
import com.vortexpolis.model.Funcion;
import com.vortexpolis.model.Pelicula;
import com.vortexpolis.model.Sala;
import org.mapstruct.*;

import java.time.LocalDateTime;
import java.util.List;

@Mapper(componentModel = "spring")
public interface FuncionMapper {

    // Entity to DTO
    @Mappings({
        @Mapping(source = "pelicula.id", target = "peliculaId"), 
        @Mapping(target = "fechaHora", expression = "java(mapFechaHora(funcion.getFecha(), funcion.getHora()))"),
        @Mapping(source = "estado", target = "estado")
    })
    FuncionDTO toDTO(Funcion funcion);

    // DTO to Entity
    @Mappings({
        @Mapping(target = "pelicula", expression = "java(mapPelicula(funcionDTO.getPeliculaId()))"),
        @Mapping(target = "fecha", expression = "java(mapFecha(funcionDTO.getFechaHora()))"),
        @Mapping(target = "hora", expression = "java(mapHora(funcionDTO.getFechaHora()))"),
        @Mapping(target = "cine", ignore = true),
        @Mapping(target = "entradas", ignore = true),
        @Mapping(target = "sala", ignore = true) // 👈 Este campo texto también se ignora por ahora
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

    default Sala mapSala(Long id) {
        if (id == null) {
            return null;
        }
        Sala sala = new Sala();
        sala.setId(id);
        return sala;
    }
}
