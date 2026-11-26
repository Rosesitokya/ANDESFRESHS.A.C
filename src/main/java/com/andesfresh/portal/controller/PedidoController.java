package com.andesfresh.portal.controller;

import com.andesfresh.portal.model.*;
import com.andesfresh.portal.service.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/pedidos")
public class PedidoController {
    private final PedidoService pedidoService;
    private final ProductoService productoService;

    public PedidoController(PedidoService pe, ProductoService pr) {
        this.pedidoService = pe;
        this.productoService = pr;
    }

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("pedidos", pedidoService.listar());
        return "pedidos/lista";
    }

    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        Pedido p = new Pedido();
        for (int i = 0; i < 5; i++) p.addDetalle(new DetallePedido());
        model.addAttribute("pedido", p);
        model.addAttribute("catalogo", productoService.listar());
        return "pedidos/formulario";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute Pedido pedido, RedirectAttributes flash) {
        pedidoService.guardar(pedido);
        flash.addFlashAttribute("mensaje", "¡Pedido registrado con éxito! Stock descontado automáticamente. Total: S/ " + String.format("%.2f", pedido.getTotal()));
        return "redirect:/pedidos";
    }
}
