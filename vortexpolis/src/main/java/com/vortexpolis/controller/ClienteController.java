package com.vortexpolis.controller;

import com.vortexpolis.dto.ClienteDTO;
import com.vortexpolis.mapper.ClienteMapper;
import com.vortexpolis.model.Cliente;
import com.vortexpolis.service.ClienteService;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private ClienteMapper clienteMapper;

    // Registrar cliente (recibe y devuelve DTO)
    @PostMapping
    public ResponseEntity<ClienteDTO> registrarCliente(@Valid @RequestBody ClienteDTO clienteDTO) {
        Cliente cliente = clienteMapper.toEntity(clienteDTO);
        Cliente nuevoCliente = clienteService.guardarCliente(cliente);
        ClienteDTO nuevoClienteDTO = clienteMapper.toDTO(nuevoCliente);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoClienteDTO);
    }

    // Listar todos los clientes (devuelve DTOs)
    @GetMapping
    public ResponseEntity<List<ClienteDTO>> listarClientes() {
        List<Cliente> clientes = clienteService.obtenerTodos();
        List<ClienteDTO> clientesDTO = clienteMapper.toDTOList(clientes);
        return ResponseEntity.ok(clientesDTO);
    }

    // Consultar cliente por ID (devuelve DTO)
    @GetMapping("/{id}")
    public ResponseEntity<ClienteDTO> obtenerClientePorId(@PathVariable Long id) {
        return clienteService.buscarPorId(id)
                .map(cliente -> ResponseEntity.ok(clienteMapper.toDTO(cliente)))
                .orElse(ResponseEntity.notFound().build());
    }
}
