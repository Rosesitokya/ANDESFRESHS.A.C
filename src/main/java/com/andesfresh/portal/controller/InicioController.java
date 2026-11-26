package com.andesfresh.portal.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class InicioController {
    @GetMapping({"/", "/inicio"}) public String inicio() { return "inicio"; }
    @GetMapping("/login") public String login() { return "login"; }
    @GetMapping("/acceso-denegado") public String accesoDenegado() { return "acceso-denegado"; }
}
