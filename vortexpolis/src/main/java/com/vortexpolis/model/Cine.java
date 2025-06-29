package com.vortexpolis.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Cine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String direccion;
    private String telefono;

    @OneToMany(mappedBy = "cine", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Funcion> funciones;
}
