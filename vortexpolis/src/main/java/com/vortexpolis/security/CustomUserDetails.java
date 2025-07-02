package com.vortexpolis.security;

import com.vortexpolis.model.Cliente;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.List;

import java.util.Collection;

public class CustomUserDetails implements UserDetails {

    private final Cliente cliente;

    public CustomUserDetails(Cliente cliente) {
        this.cliente = cliente;
    }

    public Cliente getCliente() {
        return cliente;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // El rol debe tener el prefijo "ROLE_" para que Spring lo entienda
        return List.of(() -> "ROLE_" + cliente.getRol());
    }

    @Override
    public String getPassword() {
        return cliente.getPassword();
    }

    @Override
    public String getUsername() {
        return cliente.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return cliente.isEstado(); // O simplemente return true si no manejas bloqueo
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return cliente.isEstado(); // O true si no manejas estado de activación
    }
}
