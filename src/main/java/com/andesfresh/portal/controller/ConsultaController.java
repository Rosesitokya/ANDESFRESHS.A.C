package com.andesfresh.portal.controller;

import com.andesfresh.portal.service.ProductoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/consultas")
public class ConsultaController {
    private final ProductoService service;
    public ConsultaController(ProductoService service) { this.service = service; }

    @GetMapping("/productos")
    public String buscar(@RequestParam(required = false) String marca, @RequestParam(required = false) String categoria, Model model) {
        model.addAttribute("marcas", service.marcas());
        model.addAttribute("categorias", service.categorias());
        model.addAttribute("marcaSel", marca);
        model.addAttribute("categoriaSel", categoria);
        model.addAttribute("productos", service.buscar(marca, categoria));
        return "consultas/productos";
    }

    @GetMapping("/resumen")
    public String resumen(Model model) {
        model.addAttribute("filas", service.resumenPorCategoria());
        model.addAttribute("total", service.valorTotalInventario());
        return "consultas/resumen";
    }
}
