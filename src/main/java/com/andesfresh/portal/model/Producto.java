package com.andesfresh.portal.model;

import jakarta.persistence.*;

@Entity
public class Producto {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String codigo;
    private String nombre;
    private String marca = "Andes Fresh";
    private String categoria = "Frutas";
    private Double precio = 0.0;
    private Integer cantidad = 100;

    public Producto() {}
    public Producto(String codigo, String nombre, String marca, String categoria, Double precio, Integer cantidad) {
        this.codigo = codigo; this.nombre = nombre; this.marca = marca;
        this.categoria = categoria; this.precio = precio; this.cantidad = cantidad;
    }

    public Long getId() { return id; } public void setId(Long id) { this.id = id; }
    public String getCodigo() { return codigo; } public void setCodigo(String codigo) { this.codigo = codigo; }
    public String getNombre() { return nombre; } public void setNombre(String nombre) { this.nombre = nombre; }
    public String getMarca() { return marca; } public void setMarca(String marca) { this.marca = marca; }
    public String getCategoria() { return categoria; } public void setCategoria(String categoria) { this.categoria = categoria; }
    public Double getPrecio() { return precio; } public void setPrecio(Double precio) { this.precio = precio; }
    public Integer getCantidad() { return cantidad; } public void setCantidad(Integer cantidad) { this.cantidad = cantidad; }
}
