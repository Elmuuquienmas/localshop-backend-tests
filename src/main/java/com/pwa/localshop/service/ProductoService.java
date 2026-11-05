package com.pwa.localshop.service;

import com.pwa.localshop.model.Producto;
import com.pwa.localshop.repository.ProductoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductoService {

    private final ProductoRepository productoRepository;

    public ProductoService(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    /** Recupera todos los productos. */
    public List<Producto> findAll() {
        return productoRepository.findAll();
    }

    /** Crea (persiste) un nuevo producto. */
    public Producto save(Producto producto) {
        return productoRepository.save(producto);
    }
}
