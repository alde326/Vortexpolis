package com.vortexpolis.controller;

import com.vortexpolis.dto.EntradaDTO;
import com.vortexpolis.mapper.EntradaMapper;
import com.vortexpolis.model.Entrada;
import com.vortexpolis.service.EntradaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/entradas")
public class EntradaController {

    @Autowired
    private EntradaService entradaService;

    @Autowired
    private EntradaMapper entradaMapper;

    @GetMapping
    public List<EntradaDTO> obtenerTodas() {
        List<Entrada> entradas = entradaService.listarTodas();
        return entradaMapper.toDTOList(entradas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntradaDTO> obtenerPorId(@PathVariable Long id) {
        Entrada entrada = entradaService.obtenerPorId(id);
        if (entrada != null) {
            return ResponseEntity.ok(entradaMapper.toDTO(entrada));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public EntradaDTO crear(@RequestBody EntradaDTO entradaDTO) {
        Entrada entrada = entradaMapper.toEntity(entradaDTO);
        Entrada entradaGuardada = entradaService.guardar(entrada);
        return entradaMapper.toDTO(entradaGuardada);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EntradaDTO> actualizar(@PathVariable Long id, @RequestBody EntradaDTO entradaDTO) {
        Entrada entradaExistente = entradaService.obtenerPorId(id);
        if (entradaExistente == null) {
            return ResponseEntity.notFound().build();
        }

        entradaDTO.setId(id); // Asegurarse de que el ID sea correcto
        Entrada entradaActualizada = entradaService.actualizar(entradaMapper.toEntity(entradaDTO));
        return ResponseEntity.ok(entradaMapper.toDTO(entradaActualizada));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        Entrada entrada = entradaService.obtenerPorId(id);
        if (entrada != null) {
            entradaService.eliminar(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
