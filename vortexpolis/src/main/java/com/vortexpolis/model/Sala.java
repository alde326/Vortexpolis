package com.vortexpolis.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data 
@NoArgsConstructor
public class Sala {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private Integer capacidad;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_funcion")
    private Funcion funcion;
}
