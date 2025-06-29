package com.vortexpolis.controller;

import com.vortexpolis.dto.SalaDTO;
import com.vortexpolis.mapper.SalaMapper;
import com.vortexpolis.model.Sala;
import com.vortexpolis.service.SalaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/salas")
public class SalaController {

    @Autowired
    private SalaService salaService;

    @Autowired
    private SalaMapper salaMapper;

    @GetMapping
    public List<SalaDTO> listarSalas() {
        List<Sala> salas = salaService.listarTodas();
        return salaMapper.toDTOList(salas);
    }

    @PostMapping
    public SalaDTO crearSala(@RequestBody SalaDTO salaDTO) {
        Sala sala = salaMapper.toEntity(salaDTO);
        Sala salaGuardada = salaService.guardar(sala);
        return salaMapper.toDTO(salaGuardada);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SalaDTO> obtenerSalaPorId(@PathVariable Long id) {
        Sala sala = salaService.obtenerPorId(id);
        if (sala != null) {
            return ResponseEntity.ok(salaMapper.toDTO(sala));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<SalaDTO> actualizarSala(@PathVariable Long id, @RequestBody SalaDTO salaDTO) {
        Sala salaExistente = salaService.obtenerPorId(id);
        if (salaExistente != null) {
            Sala salaActualizada = salaMapper.toEntity(salaDTO);
            salaActualizada.setId(id); // Asegurarse de actualizar la sala correcta
            Sala salaGuardada = salaService.actualizar(salaActualizada);
            return ResponseEntity.ok(salaMapper.toDTO(salaGuardada));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarSala(@PathVariable Long id) {
        Sala sala = salaService.obtenerPorId(id);
        if (sala != null) {
            salaService.eliminar(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
