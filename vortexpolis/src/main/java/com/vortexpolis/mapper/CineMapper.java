package com.vortexpolis.mapper;

import com.vortexpolis.dto.CineDTO;
import com.vortexpolis.model.Cine;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = {FuncionMapper.class})
public interface CineMapper {
    CineDTO toDTO(Cine cine);

    Cine toEntity(CineDTO cineDTO);

    List<CineDTO> toDTOList(List<Cine> cines);

    List<Cine> toEntityList(List<CineDTO> cineDTOs);
}
