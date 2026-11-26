package com.andesfresh.portal.controller;

import com.andesfresh.portal.service.UsuarioService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class UsuarioController {
    private final UsuarioService service;
    public UsuarioController(UsuarioService service) { this.service = service; }

    @GetMapping("/registro")
    public String formulario() { return "registro"; }

    @PostMapping("/registro")
    public String registrar(@RequestParam String username, @RequestParam String password, Model model) {
        try {
            service.registrar(username, password);
            return "redirect:/login?registrado";
        } catch (IllegalArgumentException e) {
            model.addAttribute("error", e.getMessage());
            return "registro";
        }
    }

    @GetMapping("/usuarios")
    public String listar(Model model) {
        model.addAttribute("usuarios", service.listar());
        return "usuarios/lista";
    }
}
