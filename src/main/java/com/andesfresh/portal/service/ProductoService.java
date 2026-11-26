package com.andesfresh.portal.service;

import com.andesfresh.portal.exception.ProductoNoEncontradoException;
import com.andesfresh.portal.model.Producto;
import com.andesfresh.portal.repository.*;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class ProductoService {
    private final ProductoRepository repo;

    public ProductoService(ProductoRepository repo) { this.repo = repo; }

    public List<Producto> listar() { return repo.findAll(); }
    public Page<Producto> listarPaginado(Pageable p) { return repo.findAll(p); }
    public Optional<Producto> buscarPorId(Long id) { return repo.findById(id); }
    public Producto buscarObligatorio(Long id) {
        return repo.findById(id).orElseThrow(() -> new ProductoNoEncontradoException(id));
    }
    public boolean existe(Long id) { return repo.existsById(id); }

    public Producto guardar(Producto p) {
        if (p.getCodigo() == null || p.getCodigo().isBlank()) {
            p.setCodigo("PRD" + (repo.count() + 101));
        }
        return repo.save(p);
    }

    public void guardarTodos(List<Producto> varios) {
        varios.removeIf(p -> p.getNombre() == null || p.getNombre().isBlank());
        varios.forEach(this::guardar);
    }

    public void eliminar(Long id) { repo.deleteById(id); }
    public List<String> marcas() { return repo.listarMarcas(); }
    public List<String> categorias() { return repo.listarCategorias(); }
    public List<Producto> buscar(String m, String c) { return repo.buscar(vacioANull(m), vacioANull(c)); }
    public List<ResumenCategoria> resumenPorCategoria() { return repo.resumenPorCategoria(); }
    public double valorTotalInventario() {
        return resumenPorCategoria().stream().mapToDouble(ResumenCategoria::getValor).sum();
    }

    private String vacioANull(String v) { return (v == null || v.isBlank()) ? null : v; }
}
