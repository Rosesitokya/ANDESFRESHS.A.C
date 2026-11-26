package com.andesfresh.portal.service;

import com.andesfresh.portal.model.*;
import com.andesfresh.portal.repository.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDate;
import java.util.*;

@Service
public class PedidoService {
    private final PedidoRepository repo;
    private final ProductoRepository productoRepo;

    public PedidoService(PedidoRepository repo, ProductoRepository productoRepo) {
        this.repo = repo;
        this.productoRepo = productoRepo;
    }

    public List<Pedido> listar() { return repo.findAll(); }

    @Transactional
    public Pedido guardar(Pedido pedido) {
        pedido.getDetalles().removeIf(d -> d.getProducto() == null || d.getProducto().isBlank()
                || d.getCantidad() == null || d.getCantidad() <= 0);
        double sub = 0;
        List<Producto> todos = productoRepo.findAll();
        for (DetallePedido d : pedido.getDetalles()) {
            d.setPedido(pedido);
            sub += (d.getPrecioUnitario() != null ? d.getPrecioUnitario() : 0.0) * d.getCantidad();
            todos.stream()
                .filter(p -> p.getNombre().equalsIgnoreCase(d.getProducto()))
                .findFirst()
                .ifPresent(p -> {
                    p.setCantidad(Math.max(0, p.getCantidad() - d.getCantidad().intValue()));
                    productoRepo.save(p);
                });
        }
        double desc = sub >= 1000.0 ? sub * 0.05 : 0.0;
        pedido.setFecha(LocalDate.now());
        pedido.setEstado("REGISTRADO");
        pedido.setSubtotal(sub);
        pedido.setDescuento(desc);
        pedido.setTotal(sub - desc);
        return repo.save(pedido);
    }
}
