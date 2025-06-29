package com.vortexpolis.mapper;

import com.vortexpolis.dto.SalaDTO;
import com.vortexpolis.model.Sala;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SalaMapper {

    SalaDTO toDTO(Sala sala);

    Sala toEntity(SalaDTO salaDTO);

    List<SalaDTO> toDTOList(List<Sala> salas);

    List<Sala> toEntityList(List<SalaDTO> salaDTOs);
}
