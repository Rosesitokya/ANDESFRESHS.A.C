package com.andesfresh.portal.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDate;

@Entity
public class Empleado {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "Ingrese los nombres") private String nombres;
    @NotBlank(message = "Ingrese los apellidos") private String apellidos;
    @NotNull(message = "Ingrese la fecha de ingreso") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) private LocalDate fechaIngreso;
    @NotNull(message = "Ingrese el sueldo") @Positive(message = "El sueldo debe ser mayor que cero") private Double sueldo;
    @NotBlank(message = "Ingrese la dirección") private String direccion;

    public Empleado() {}
    public Empleado(String n, String a, LocalDate f, Double s, String d) {
        this.nombres = n; this.apellidos = a; this.fechaIngreso = f; this.sueldo = s; this.direccion = d;
    }

    public Long getId() { return id; } public void setId(Long id) { this.id = id; }
    public String getNombres() { return nombres; } public void setNombres(String nombres) { this.nombres = nombres; }
    public String getApellidos() { return apellidos; } public void setApellidos(String apellidos) { this.apellidos = apellidos; }
    public LocalDate getFechaIngreso() { return fechaIngreso; } public void setFechaIngreso(LocalDate fechaIngreso) { this.fechaIngreso = fechaIngreso; }
    public Double getSueldo() { return sueldo; } public void setSueldo(Double sueldo) { this.sueldo = sueldo; }
    public String getDireccion() { return direccion; } public void setDireccion(String direccion) { this.direccion = direccion; }
}
