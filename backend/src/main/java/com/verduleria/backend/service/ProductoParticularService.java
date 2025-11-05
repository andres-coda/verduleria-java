package com.verduleria.backend.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.verduleria.backend.entity.ProductoParticular;
import com.verduleria.backend.repository.ProductoParticularRepository;

@Service
public class ProductoParticularService {

    private final ProductoParticularRepository productoParticularRepository;

    public ProductoParticularService(ProductoParticularRepository productoParticularRepository) {
        this.productoParticularRepository = productoParticularRepository;
    }

    public List<ProductoParticular> getProductosByUsuario(Long usuarioId) {
        return productoParticularRepository.findByUsuarioId(usuarioId);
    }

    public List<ProductoParticular> getAllProductosParticulares() {
        return productoParticularRepository.findAll();
    }

    public ProductoParticular getProductoParticularById(Long id) {
        return productoParticularRepository.findById(id).orElse(null);
    }

    public ProductoParticular createProductoParticular(ProductoParticular productoParticular) {
        return productoParticularRepository.save(productoParticular);
    }

    public ProductoParticular updateProductoParticular(Long id, ProductoParticular actualizado) {
        return productoParticularRepository.findById(id)
            .map(pp -> {
                pp.setNombre(actualizado.getNombre());
                pp.setRubro(actualizado.getRubro());
                pp.setPrecio(actualizado.getPrecio());
                pp.setMedida(actualizado.getMedida());
                pp.setPorcentajeAumento(actualizado.getPorcentajeAumento());
                return productoParticularRepository.save(pp);
            })
            .orElse(null);
    }

    public void deleteProductoParticular(Long id) {
        productoParticularRepository.deleteById(id);
    }
}

