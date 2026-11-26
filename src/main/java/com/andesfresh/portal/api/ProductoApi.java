package com.andesfresh.portal.api;

import com.andesfresh.portal.model.Producto;
import com.andesfresh.portal.service.ProductoService;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/productos")
public class ProductoApi {
    private final ProductoService service;
    public ProductoApi(ProductoService service) { this.service = service; }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Producto> listar() { return service.listar(); }

    @GetMapping(value = "/xml", produces = MediaType.APPLICATION_XML_VALUE)
    public List<Producto> listarXml() { return service.listar(); }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Producto> buscar(@PathVariable Long id) {
        return service.buscarPorId(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Producto> crear(@RequestBody Producto p) {
        p.setId(null);
        Producto n = service.guardar(p);
        return ResponseEntity.created(URI.create("/api/productos/" + n.getId())).body(n);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Producto> actualizar(@PathVariable Long id, @RequestBody Producto p) {
        if (!service.existe(id)) return ResponseEntity.notFound().build();
        p.setId(id);
        return ResponseEntity.ok(service.guardar(p));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        if (!service.existe(id)) return ResponseEntity.notFound().build();
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
