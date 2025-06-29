package com.vortexpolis.mapper;

import com.vortexpolis.dto.EntradaDTO;
import com.vortexpolis.model.Entrada;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EntradaMapper {

    EntradaDTO toDTO(Entrada entrada);

    Entrada toEntity(EntradaDTO entradaDTO);

    List<EntradaDTO> toDTOList(List<Entrada> entradas);

    List<Entrada> toEntityList(List<EntradaDTO> entradaDTOs);
}
