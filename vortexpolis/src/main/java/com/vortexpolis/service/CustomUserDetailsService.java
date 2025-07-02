package com.vortexpolis.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.List;
import org.springframework.security.core.userdetails.User;

import com.vortexpolis.model.Cliente;
import com.vortexpolis.repository.ClienteRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        System.out.println("🔍 Buscando usuario con email: " + email);
        Cliente cliente = clienteRepository.findByEmail(email)
                .orElseThrow(() -> {
                    System.out.println("❌ Usuario no encontrado: " + email);
                    return new UsernameNotFoundException("Usuario no encontrado");
                });

        System.out.println("✅ Usuario encontrado: " + cliente.getEmail());

        List<GrantedAuthority> authorities = List.of(new SimpleGrantedAuthority(cliente.getRoles()));

            return new User(cliente.getEmail(), cliente.getPassword(), authorities);
            }
}
