package com.vortexpolis.mapper;

import com.vortexpolis.dto.CompraDTO;
import com.vortexpolis.model.Compra;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CompraMapper {

    CompraDTO toDTO(Compra compra);

    Compra toEntity(CompraDTO compraDTO);

    List<CompraDTO> toDTOList(List<Compra> compras);

    List<Compra> toEntityList(List<CompraDTO> compraDTOs);
}
