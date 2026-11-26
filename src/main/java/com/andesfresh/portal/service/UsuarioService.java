package com.andesfresh.portal.service;

import com.andesfresh.portal.model.*;
import com.andesfresh.portal.repository.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UsuarioService {
    private final UsuarioRepository usuarioRepo;
    private final RolRepository rolRepo;
    private final PasswordEncoder encoder;

    public UsuarioService(UsuarioRepository u, RolRepository r, PasswordEncoder e) {
        this.usuarioRepo = u; this.rolRepo = r; this.encoder = e;
    }

    public void registrar(String username, String clave) {
        if (usuarioRepo.existsByUsername(username)) {
            throw new IllegalArgumentException("El usuario ya existe.");
        }
        if (clave.length() < 8) {
            throw new IllegalArgumentException("La contraseña debe tener al menos 8 caracteres.");
        }
        Rol vendedor = rolRepo.findByNombre("VENDEDOR").orElseThrow();
        Usuario u = new Usuario(username, encoder.encode(clave));
        u.getRoles().add(vendedor);
        usuarioRepo.save(u);
    }

    public List<Usuario> listar() { return usuarioRepo.findAll(); }
}
