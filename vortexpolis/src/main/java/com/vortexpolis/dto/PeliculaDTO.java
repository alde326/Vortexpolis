package com.vortexpolis.dto;

import lombok.Data;

@Data
public class PeliculaDTO {
    private Long id;
    private String titulo;
    private String descripcion;
    private String imagenUrl;
    private boolean activa;
}
