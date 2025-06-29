package com.vortexpolis.mapper;

import com.vortexpolis.dto.MetodoPagoDTO;
import com.vortexpolis.model.MetodoPago;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MetodoPagoMapper {

    MetodoPagoDTO toDTO(MetodoPago metodoPago);

    MetodoPago toEntity(MetodoPagoDTO metodoPagoDTO);

    List<MetodoPagoDTO> toDTOList(List<MetodoPago> metodosPago);

    List<MetodoPago> toEntityList(List<MetodoPagoDTO> metodoPagoDTOs);
}
