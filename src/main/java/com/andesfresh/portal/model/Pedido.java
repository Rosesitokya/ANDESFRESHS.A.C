package com.andesfresh.portal.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.*;

@Entity
public class Pedido {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String rucCliente;
    private String cliente;
    private LocalDate fecha = LocalDate.now();
    private String estado = "REGISTRADO";
    private Double subtotal = 0.0;
    private Double descuento = 0.0;
    private Double total = 0.0;

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<DetallePedido> detalles = new ArrayList<>();

    public Pedido() {}
    public Pedido(String ruc, String cliente, LocalDate fecha, Double total, String estado) {
        this.rucCliente = ruc; this.cliente = cliente; this.fecha = fecha;
        this.subtotal = total; this.descuento = 0.0; this.total = total; this.estado = estado;
    }

    public void addDetalle(DetallePedido d) { d.setPedido(this); detalles.add(d); }
    public Long getId() { return id; } public void setId(Long id) { this.id = id; }
    public String getRucCliente() { return rucCliente; } public void setRucCliente(String rucCliente) { this.rucCliente = rucCliente; }
    public String getCliente() { return cliente; } public void setCliente(String cliente) { this.cliente = cliente; }
    public LocalDate getFecha() { return fecha; } public void setFecha(LocalDate fecha) { this.fecha = fecha; }
    public String getEstado() { return estado; } public void setEstado(String estado) { this.estado = estado; }
    public Double getSubtotal() { return subtotal; } public void setSubtotal(Double subtotal) { this.subtotal = subtotal; }
    public Double getDescuento() { return descuento; } public void setDescuento(Double descuento) { this.descuento = descuento; }
    public Double getTotal() { return total; } public void setTotal(Double total) { this.total = total; }
    public List<DetallePedido> getDetalles() { return detalles; } public void setDetalles(List<DetallePedido> detalles) { this.detalles = detalles; }
}
