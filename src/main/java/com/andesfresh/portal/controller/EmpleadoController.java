package com.andesfresh.portal.controller;

import com.andesfresh.portal.model.Empleado;
import com.andesfresh.portal.service.EmpleadoService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/empleados")
public class EmpleadoController {
    private final EmpleadoService service;
    public EmpleadoController(EmpleadoService service) { this.service = service; }

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("empleados", service.listar());
        return "empleados/lista";
    }

    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        model.addAttribute("empleado", new Empleado());
        model.addAttribute("titulo", "Registro de empleados");
        return "empleados/form";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model, RedirectAttributes flash) {
        return service.buscarPorId(id)
            .map(e -> {
                model.addAttribute("empleado", e);
                model.addAttribute("titulo", "Editar empleado");
                return "empleados/form";
            })
            .orElseGet(() -> {
                flash.addFlashAttribute("mensaje", "El empleado no existe");
                return "redirect:/empleados";
            });
    }

    @PostMapping("/guardar")
    public String guardar(@Valid @ModelAttribute("empleado") Empleado e, BindingResult r, Model m, RedirectAttributes flash) {
        if (r.hasErrors()) {
            m.addAttribute("titulo", e.getId() == null ? "Registro de empleados" : "Editar empleado");
            return "empleados/form";
        }
        service.guardar(e);
        flash.addFlashAttribute("mensaje", "Empleado guardado correctamente");
        return "redirect:/empleados";
    }

    @PostMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id, RedirectAttributes flash) {
        service.eliminar(id);
        flash.addFlashAttribute("mensaje", "Empleado eliminado");
        return "redirect:/empleados";
    }
}
