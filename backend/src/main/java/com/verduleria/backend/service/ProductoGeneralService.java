package com.verduleria.backend.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.verduleria.backend.entity.ProductoGeneral;
import com.verduleria.backend.repository.ProductoGeneralRepository;

@Service
public class ProductoGeneralService {

    private final ProductoGeneralRepository productoGeneralRepository;

    public ProductoGeneralService(ProductoGeneralRepository productoGeneralRepository) {
        this.productoGeneralRepository = productoGeneralRepository;
    }

    public List<ProductoGeneral> getAllProductosGenerales() {
        return productoGeneralRepository.findAll();
    }

    public ProductoGeneral getProductoGeneralById(Long id) {
        return productoGeneralRepository.findById(id).orElse(null);
    }

    public ProductoGeneral createProductoGeneral(ProductoGeneral productoGeneral) {
        return productoGeneralRepository.save(productoGeneral);
    }

    public ProductoGeneral updateProductoGeneral(Long id, ProductoGeneral actualizado) {
        return productoGeneralRepository.findById(id)
            .map(pg -> {
                pg.setNombre(actualizado.getNombre());
                pg.setRubro(actualizado.getRubro());
                return productoGeneralRepository.save(pg);
            })
            .orElse(null);
    }

    public void deleteProductoGeneral(Long id) {
        productoGeneralRepository.deleteById(id);
    }
}

