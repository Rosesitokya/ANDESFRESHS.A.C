package com.andesfresh.portal.dto;

import com.andesfresh.portal.model.Producto;
import java.util.*;

public class LoteProductoForm {
    private List<Producto> productos = new ArrayList<>();
    public List<Producto> getProductos() { return productos; }
    public void setProductos(List<Producto> productos) { this.productos = productos; }
}
