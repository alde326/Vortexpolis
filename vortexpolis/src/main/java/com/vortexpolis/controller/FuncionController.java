package com.vortexpolis.controller;

import com.vortexpolis.dto.FuncionDTO;
import com.vortexpolis.mapper.FuncionMapper;
import com.vortexpolis.model.Funcion;
import com.vortexpolis.service.FuncionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/funciones")
public class FuncionController {

    @Autowired
    private FuncionService funcionService;

    @Autowired
    private FuncionMapper funcionMapper;

    @PostMapping
    public FuncionDTO crearFuncion(@RequestBody FuncionDTO funcionDTO) {
        Funcion funcion = funcionMapper.toEntity(funcionDTO);
        Funcion funcionGuardada = funcionService.crearFuncion(funcion);
        return funcionMapper.toDTO(funcionGuardada);
    }

    @GetMapping("/pelicula/{peliculaId}")
    public ResponseEntity<List<FuncionDTO>> listarFuncionesPorPelicula(@PathVariable Long peliculaId) {
        List<Funcion> funciones = funcionService.listarFuncionesPorPelicula(peliculaId);
        if (funciones.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(funcionMapper.toDTOList(funciones));
        }
    }
}
