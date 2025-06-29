package com.vortexpolis.controller;

import com.vortexpolis.dto.PagoDTO;
import com.vortexpolis.mapper.PagoMapper;
import com.vortexpolis.model.Pago;
import com.vortexpolis.service.PagoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pagos")
public class PagoController {

    @Autowired
    private PagoService pagoService;

    @Autowired
    private PagoMapper pagoMapper;

    @PostMapping
    public PagoDTO crearPago(@RequestBody PagoDTO pagoDTO) {
        Pago pago = pagoMapper.toEntity(pagoDTO);
        Pago pagoGuardado = pagoService.guardar(pago);
        return pagoMapper.toDTO(pagoGuardado);
    }

    @GetMapping
    public List<PagoDTO> listarPagos() {
        List<Pago> pagos = pagoService.listarTodos();
        return pagoMapper.toDTOList(pagos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PagoDTO> obtenerPagoPorId(@PathVariable Long id) {
        Pago pago = pagoService.obtenerPorId(id);
        if (pago != null) {
            return ResponseEntity.ok(pagoMapper.toDTO(pago));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<PagoDTO> actualizarPago(@PathVariable Long id, @RequestBody PagoDTO pagoDTO) {
        Pago pagoExistente = pagoService.obtenerPorId(id);
        if (pagoExistente != null) {
            Pago pagoActualizado = pagoMapper.toEntity(pagoDTO);
            pagoActualizado.setId(id);
            Pago pagoGuardado = pagoService.actualizar(pagoActualizado);
            return ResponseEntity.ok(pagoMapper.toDTO(pagoGuardado));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarPago(@PathVariable Long id) {
        Pago pagoExistente = pagoService.obtenerPorId(id);
        if (pagoExistente != null) {
            pagoService.eliminar(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
