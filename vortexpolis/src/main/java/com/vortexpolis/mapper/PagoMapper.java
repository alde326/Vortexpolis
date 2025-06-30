package com.vortexpolis.mapper;

import com.vortexpolis.dto.PagoDTO;
import com.vortexpolis.model.Compra;
import com.vortexpolis.model.MetodoPago;
import com.vortexpolis.model.Pago;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PagoMapper {

    // De Pago (entity) a PagoDTO
    @Mappings({
        @Mapping(source = "compra.id", target = "compraId"),
        @Mapping(source = "metodoPago.id", target = "metodoPagoId")
    })
    PagoDTO toDTO(Pago pago);

    // De PagoDTO a Pago (entity)
    @Mappings({
        @Mapping(source = "compraId", target = "compra"),
        @Mapping(source = "metodoPagoId", target = "metodoPago")
    })
    Pago toEntity(PagoDTO pagoDTO);

    List<PagoDTO> toDTOList(List<Pago> pagos);

    List<Pago> toEntityList(List<PagoDTO> pagoDTOs);

    // Métodos default para convertir IDs en entidades mínimas

    default Compra mapCompraId(Long id) {
        if (id == null) {
            return null;
        }
        Compra compra = new Compra();
        compra.setId(id);
        return compra;
    }

    default MetodoPago mapMetodoPagoId(Long id) {
        if (id == null) {
            return null;
        }
        MetodoPago metodoPago = new MetodoPago();
        metodoPago.setId(id);
        return metodoPago;
    }
}
