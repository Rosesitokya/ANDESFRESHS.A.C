package com.andesfresh.portal.service;

import com.andesfresh.portal.model.*;
import com.andesfresh.portal.repository.UsuarioRepository;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

@Service
public class UsuarioDetailsService implements UserDetailsService {
    private final UsuarioRepository repo;

    public UsuarioDetailsService(UsuarioRepository repo) { this.repo = repo; }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario u = repo.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));
        String[] roles = u.getRoles().stream().map(Rol::getNombre).toArray(String[]::new);
        return User.withUsername(u.getUsername())
                .password(u.getPassword())
                .roles(roles)
                .disabled(!u.isActivo())
                .build();
    }
}
