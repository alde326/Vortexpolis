package com.vortexpolis.mapper;

import com.vortexpolis.dto.PagoDTO;
import com.vortexpolis.model.Pago;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PagoMapper {

    PagoDTO toDTO(Pago pago);

    Pago toEntity(PagoDTO pagoDTO);

    List<PagoDTO> toDTOList(List<Pago> pagos);

    List<Pago> toEntityList(List<PagoDTO> pagoDTOs);
}
