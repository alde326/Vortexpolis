package com.vortexpolis.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class MetodoPago {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String descripcion;

    @OneToMany(mappedBy = "metodoPago", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Pago> pagos;
}
