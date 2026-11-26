package com.andesfresh.portal.model;

import jakarta.persistence.*;

@Entity
public class DetallePedido {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String producto;
    private Double precioUnitario = 0.0;
    private Double cantidad = 0.0;

    @ManyToOne @JoinColumn(name = "pedido_id")
    private Pedido pedido;

    public Long getId() { return id; } public void setId(Long id) { this.id = id; }
    public String getProducto() { return producto; } public void setProducto(String producto) { this.producto = producto; }
    public Double getPrecioUnitario() { return precioUnitario; } public void setPrecioUnitario(Double precioUnitario) { this.precioUnitario = precioUnitario; }
    public Double getCantidad() { return cantidad; } public void setCantidad(Double cantidad) { this.cantidad = cantidad; }
    public Pedido getPedido() { return pedido; } public void setPedido(Pedido pedido) { this.pedido = pedido; }
}
