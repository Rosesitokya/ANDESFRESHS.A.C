package com.andesfresh.portal.controller;

import com.andesfresh.portal.model.Proveedor;
import com.andesfresh.portal.service.ProveedorService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/proveedores")
public class ProveedorController {
    private final ProveedorService service;
    public ProveedorController(ProveedorService service) { this.service = service; }

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("proveedores", service.listar());
        return "proveedores/lista";
    }

    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        model.addAttribute("proveedor", new Proveedor());
        return "proveedores/formulario";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute Proveedor p, RedirectAttributes flash) {
        service.guardar(p);
        flash.addFlashAttribute("mensaje", "Proveedor " + p.getRazonSocial() + " guardado correctamente.");
        return "redirect:/proveedores";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/{id}/eliminar")
    public String eliminar(@PathVariable Long id, RedirectAttributes flash) {
        service.eliminar(id);
        flash.addFlashAttribute("mensaje", "Proveedor eliminado.");
        return "redirect:/proveedores";
    }
}
