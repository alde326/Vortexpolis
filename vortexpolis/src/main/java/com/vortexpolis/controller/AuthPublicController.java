package com.vortexpolis.controller;

import com.vortexpolis.dto.ClienteDTO;
import com.vortexpolis.model.Cliente;
import com.vortexpolis.service.ClienteService;
import com.vortexpolis.mapper.ClienteMapper;

import org.springframework.security.core.Authentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Optional;


@RestController
@RequestMapping("/api/public")
public class AuthPublicController {

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private ClienteMapper clienteMapper;

    @PostMapping("/register")
    public ResponseEntity<?> registrarCliente(@RequestBody Cliente cliente) {
        if (clienteService.emailExistente(cliente.getEmail())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("El email ya está registrado");
        }

        Cliente nuevoCliente = clienteService.guardarCliente(cliente);
        ClienteDTO clienteDTO = clienteMapper.toDTO(nuevoCliente);

        return ResponseEntity.status(HttpStatus.CREATED).body(clienteDTO);
    }

    @GetMapping("/auth/user")
    public ResponseEntity<?> getAuthenticatedUser(Authentication authentication) {
        if (authentication != null && authentication.isAuthenticated()) {
            return ResponseEntity.ok(authentication.getAuthorities());
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("No autenticado");
    }

    @GetMapping("/auth/id")
    public ResponseEntity<?> getUserId(Authentication authentication) {
        if (authentication != null && authentication.isAuthenticated()) {
            String email = authentication.getName();
            Optional<Cliente> optionalCliente = clienteService.buscarPorEmail(email);

            if (optionalCliente.isPresent()) {
                Cliente cliente = optionalCliente.get();
                return ResponseEntity.ok(cliente.getId());
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente no encontrado");
            }
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("No autenticado");
    }



}
