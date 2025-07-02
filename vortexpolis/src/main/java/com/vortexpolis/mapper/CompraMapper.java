package com.vortexpolis.mapper;

import com.vortexpolis.dto.CompraDTO;
import com.vortexpolis.model.Compra;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CompraMapper {

    public CompraDTO toDTO(Compra compra) {
        CompraDTO dto = new CompraDTO();
        dto.setId(compra.getId());
        dto.setFecha(compra.getFecha());
        dto.setTotal(compra.getTotal());
        dto.setEstado(compra.getEstado());
        dto.setClienteId(compra.getCliente().getId());

        // Ya no puedes convertir entradas porque eliminaste la relación
        dto.setEntradasIds(null); // O elimina esta línea si no quieres devolverlo

        return dto;
    }

    public List<CompraDTO> toDTOList(List<Compra> compras) {
        return compras.stream().map(this::toDTO).collect(Collectors.toList());
    }
}