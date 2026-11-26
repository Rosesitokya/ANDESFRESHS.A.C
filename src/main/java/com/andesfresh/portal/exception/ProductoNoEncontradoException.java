package com.andesfresh.portal.exception;

public class ProductoNoEncontradoException extends RuntimeException {
    public ProductoNoEncontradoException(Long id) {
        super("No existe el producto con código " + id);
    }
}
