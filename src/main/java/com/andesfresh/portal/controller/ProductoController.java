package com.andesfresh.portal.controller;

import com.andesfresh.portal.dto.LoteProductoForm;
import com.andesfresh.portal.model.Producto;
import com.andesfresh.portal.service.ProductoService;
import org.springframework.data.domain.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/productos")
public class ProductoController {
    private final ProductoService service;
    public ProductoController(ProductoService service) { this.service = service; }

    @GetMapping
    public String listar(@RequestParam(defaultValue = "0") int page, Model model) {
        model.addAttribute("productos", service.listarPaginado(PageRequest.of(page, 5, Sort.by("id").descending())));
        return "productos/lista";
    }

    @GetMapping("/inventario")
    public String inventario(Model model) {
        model.addAttribute("productos", service.listar());
        return "productos/inventario";
    }

    @GetMapping("/{id}")
    public String detalle(@PathVariable Long id, Model model) {
        model.addAttribute("producto", service.buscarObligatorio(id));
        return "productos/detalle";
    }

    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        model.addAttribute("producto", new Producto());
        return "productos/formulario";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute Producto p) {
        service.guardar(p);
        return "redirect:/productos";
    }

    @GetMapping("/lote")
    public String formularioLote(Model model) {
        LoteProductoForm lote = new LoteProductoForm();
        for (int i = 0; i < 3; i++) lote.getProductos().add(new Producto());
        model.addAttribute("lote", lote);
        return "productos/lote";
    }

    @PostMapping("/lote")
    public String guardarLote(@ModelAttribute("lote") LoteProductoForm lote) {
        service.guardarTodos(lote.getProductos());
        return "redirect:/productos";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/{id}/eliminar")
    public String eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return "redirect:/productos";
    }
}
