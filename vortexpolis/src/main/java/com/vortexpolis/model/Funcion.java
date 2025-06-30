package com.vortexpolis.model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data 
@NoArgsConstructor
public class Funcion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate fecha;
    private LocalTime hora;
    private String sala;

    private Boolean estado = true; 

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_cine")
    private Cine cine;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_pelicula")
    private Pelicula pelicula;

    @OneToMany(mappedBy = "funcion", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Entrada> entradas;

}
