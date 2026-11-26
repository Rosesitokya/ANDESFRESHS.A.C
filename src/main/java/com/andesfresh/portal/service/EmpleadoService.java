package com.andesfresh.portal.service;

import com.andesfresh.portal.model.Empleado;
import com.andesfresh.portal.repository.EmpleadoRepository;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class EmpleadoService {
    private final EmpleadoRepository repo;
    public EmpleadoService(EmpleadoRepository repo) { this.repo = repo; }
    public List<Empleado> listar() { return repo.findAll(); }
    public Optional<Empleado> buscarPorId(Long id) { return repo.findById(id); }
    public Empleado guardar(Empleado e) { return repo.save(e); }
    public void eliminar(Long id) { repo.deleteById(id); }
}
