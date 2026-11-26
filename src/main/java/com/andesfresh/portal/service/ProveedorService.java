package com.andesfresh.portal.service;

import com.andesfresh.portal.model.Proveedor;
import com.andesfresh.portal.repository.ProveedorRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ProveedorService {
    private final ProveedorRepository repo;
    public ProveedorService(ProveedorRepository repo) { this.repo = repo; }
    public List<Proveedor> listar() { return repo.findAll(); }
    public Proveedor guardar(Proveedor p) { return repo.save(p); }
    public void eliminar(Long id) { repo.deleteById(id); }
}
