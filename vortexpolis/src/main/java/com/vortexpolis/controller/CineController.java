package com.vortexpolis.controller;

import com.vortexpolis.dto.CineDTO;
import com.vortexpolis.mapper.CineMapper;
import com.vortexpolis.model.Cine;
import com.vortexpolis.service.CineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cines")
public class CineController {

    @Autowired
    private CineService cineService;

    @Autowired
    private CineMapper cineMapper;

    // Obtener todos los cines (devuelve DTOs)
    @GetMapping
    public ResponseEntity<List<CineDTO>> obtenerTodos() {
        List<Cine> cines = cineService.listarTodos();
        List<CineDTO> cinesDTO = cineMapper.toDTOList(cines);
        return ResponseEntity.ok(cinesDTO);
    }

    // Obtener cine por ID (devuelve DTO)
    @GetMapping("/{id}")
    public ResponseEntity<CineDTO> obtenerPorId(@PathVariable Long id) {
        Cine cine = cineService.obtenerPorId(id);
        if (cine != null) {
            CineDTO cineDTO = cineMapper.toDTO(cine);
            return ResponseEntity.ok(cineDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Crear cine (recibe y devuelve DTO)
    @PostMapping
    public ResponseEntity<CineDTO> crear(@RequestBody CineDTO cineDTO) {
        Cine cine = cineMapper.toEntity(cineDTO);
        Cine cineGuardado = cineService.guardar(cine);
        CineDTO cineGuardadoDTO = cineMapper.toDTO(cineGuardado);
        return ResponseEntity.ok(cineGuardadoDTO);
    }

    // Actualizar cine (recibe y devuelve DTO)
    @PutMapping("/{id}")
    public ResponseEntity<CineDTO> actualizar(@PathVariable Long id, @RequestBody CineDTO cineDTO) {
        Cine cineExistente = cineService.obtenerPorId(id);
        if (cineExistente == null) {
            return ResponseEntity.notFound().build();
        }

        // Asegurar que el ID sea correcto
        cineDTO.setId(id);
        Cine cineActualizado = cineService.actualizar(cineMapper.toEntity(cineDTO));
        CineDTO cineActualizadoDTO = cineMapper.toDTO(cineActualizado);
        return ResponseEntity.ok(cineActualizadoDTO);
    }

    // Eliminar cine
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        Cine cine = cineService.obtenerPorId(id);
        if (cine != null) {
            cineService.eliminar(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
