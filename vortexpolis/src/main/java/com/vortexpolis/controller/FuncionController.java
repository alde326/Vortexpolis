package com.vortexpolis.controller;

// import com.vortexpolis.dto.EntradaDTO;
import com.vortexpolis.dto.FuncionDTO;
import com.vortexpolis.mapper.FuncionMapper;
// import com.vortexpolis.model.Entrada;
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
        // ✅ Aquí ya no haces el mapeo, el service lo hace
        Funcion funcionGuardada = funcionService.crearFuncion(funcionDTO);
        return funcionMapper.toDTO(funcionGuardada);
    }

    @GetMapping
    public ResponseEntity<List<FuncionDTO>> listarTodasLasFunciones() {
        List<Funcion> funciones = funcionService.listarTodas();
        return ResponseEntity.ok(funcionMapper.toDTOList(funciones));
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

    @GetMapping("/{id}")
    public ResponseEntity<FuncionDTO> obtenerPorId(@PathVariable Long id) {
        Funcion funcion = funcionService.obtenerPorId(id);
        if (funcion != null) {
            return ResponseEntity.ok(funcionMapper.toDTO(funcion));
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
