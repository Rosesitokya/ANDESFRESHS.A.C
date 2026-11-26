package com.andesfresh.portal.config;

import com.andesfresh.portal.model.*;
import com.andesfresh.portal.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import java.time.LocalDate;
import java.util.List;

@Component
public class CargaInicial implements CommandLineRunner {
    private final RolRepository rolRepo;
    private final UsuarioRepository usuarioRepo;
    private final ProductoRepository productoRepo;
    private final EmpleadoRepository empleadoRepo;
    private final ProveedorRepository proveedorRepo;
    private final PedidoRepository pedidoRepo;
    private final PasswordEncoder encoder;

    public CargaInicial(RolRepository r, UsuarioRepository u, ProductoRepository p, EmpleadoRepository e,
                        ProveedorRepository pr, PedidoRepository pe, PasswordEncoder enc) {
        this.rolRepo = r; this.usuarioRepo = u; this.productoRepo = p; this.empleadoRepo = e;
        this.proveedorRepo = pr; this.pedidoRepo = pe; this.encoder = enc;
    }

    @Override
    public void run(String... args) {
        Rol admin = rolRepo.save(new Rol("ADMIN"));
        Rol vendedor = rolRepo.save(new Rol("VENDEDOR"));
        Usuario u1 = new Usuario("admin", encoder.encode("Admin2026")); u1.getRoles().add(admin); usuarioRepo.save(u1);
        Usuario u2 = new Usuario("vendedor", encoder.encode("Venta2026")); u2.getRoles().add(vendedor); usuarioRepo.save(u2);

        productoRepo.saveAll(List.of(
            new Producto("PAL001", "Palta Hass", "Valle Norte", "Frutas", 7.80, 380),
            new Producto("MAN002", "Mango Kent", "Valle Norte", "Frutas", 4.50, 20),
            new Producto("ARA003", "Arándano", "Sierra Viva", "Frutas", 18.00, 40),
            new Producto("PAP004", "Papa amarilla", "Sierra Viva", "Tubérculos", 3.20, 300),
            new Producto("CAM005", "Camote", "Costa Verde", "Tubérculos", 2.10, 180),
            new Producto("QUI006", "Quinua blanca", "Sierra Viva", "Granos", 12.50, 75),
            new Producto("KIW007", "Kiwicha", "Costa Verde", "Granos", 14.00, 30),
            new Producto("ESP008", "Espárrago verde", "Costa Verde", "Verduras", 9.90, 60)
        ));

        empleadoRepo.saveAll(List.of(
            new Empleado("Rosa", "Quispe Huamán", LocalDate.of(2023, 3, 1), 2800.0, "Av. Los Frutales 120, Ate"),
            new Empleado("Luis", "Mendoza Ríos", LocalDate.of(2024, 7, 15), 2300.0, "Jr. Huancavelica 455, Lima"),
            new Empleado("Carla", "Torres Vega", LocalDate.of(2025, 1, 10), 3100.0, "Av. La Molina 890, La Molina")
        ));

        proveedorRepo.save(new Proveedor("20456789012", "Agrícola Valle Norte S.A.C.", "ventas@vallenorte.pe", "987654321", LocalDate.of(2025, 1, 15), "Frutas", "Productor Directo"));

        pedidoRepo.saveAll(List.of(
            new Pedido("20601234567", "Restaurante El Tambo SAC", LocalDate.of(2026, 10, 29), 984.20, "DESPACHADO"),
            new Pedido("10701234567", "Frutería Los Andes", LocalDate.of(2026, 10, 29), 150.00, "REGISTRADO")
        ));
    }
}
