package com.vortexpolis.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Entrada {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double precioUnitario;
    private String estado;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_compra")
    @JsonBackReference  
    private Compra compra;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_pelicula")
    private Pelicula pelicula;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_funcion")
    private Funcion funcion;
}
