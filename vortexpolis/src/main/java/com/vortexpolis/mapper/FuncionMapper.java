package com.vortexpolis.mapper;

import com.vortexpolis.dto.FuncionDTO;
import com.vortexpolis.model.Funcion;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface FuncionMapper {

    FuncionDTO toDTO(Funcion funcion);

    Funcion toEntity(FuncionDTO funcionDTO);

    List<FuncionDTO> toDTOList(List<Funcion> funciones);

    List<Funcion> toEntityList(List<FuncionDTO> funcionDTOs);
}
