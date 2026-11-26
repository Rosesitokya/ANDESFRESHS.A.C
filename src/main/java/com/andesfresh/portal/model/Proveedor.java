package com.andesfresh.portal.model;

import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDate;

@Entity
public class Proveedor {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String ruc;
    private String razonSocial;
    private String correo;
    private String telefono;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate inicioContrato;
    private String categoria;
    private String tipo;

    public Proveedor() {}
    public Proveedor(String ruc, String razon, String correo, String tel, LocalDate ini, String cat, String tipo) {
        this.ruc = ruc; this.razonSocial = razon; this.correo = correo; this.telefono = tel;
        this.inicioContrato = ini; this.categoria = cat; this.tipo = tipo;
    }

    public Long getId() { return id; } public void setId(Long id) { this.id = id; }
    public String getRuc() { return ruc; } public void setRuc(String ruc) { this.ruc = ruc; }
    public String getRazonSocial() { return razonSocial; } public void setRazonSocial(String razonSocial) { this.razonSocial = razonSocial; }
    public String getCorreo() { return correo; } public void setCorreo(String correo) { this.correo = correo; }
    public String getTelefono() { return telefono; } public void setTelefono(String telefono) { this.telefono = telefono; }
    public LocalDate getInicioContrato() { return inicioContrato; } public void setInicioContrato(LocalDate inicioContrato) { this.inicioContrato = inicioContrato; }
    public String getCategoria() { return categoria; } public void setCategoria(String categoria) { this.categoria = categoria; }
    public String getTipo() { return tipo; } public void setTipo(String tipo) { this.tipo = tipo; }
}
