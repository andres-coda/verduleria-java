package com.verduleria.backend.service;

import com.verduleria.backend.entity.ProductoLista;
import com.verduleria.backend.repository.ProductoListaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductoListaService {

  private final ProductoListaRepository productoListaRepository;

  public ProductoListaService(ProductoListaRepository productoListaRepository) {
    this.productoListaRepository = productoListaRepository;
  }

  public List<ProductoLista> getAllProductosLista() {
    return productoListaRepository.findAll();
  }

  public ProductoLista getProductoListaById(Long id) {
    return productoListaRepository.findById(id).orElse(null);
  }

  public ProductoLista createProductoLista(ProductoLista productoLista) {
    return productoListaRepository.save(productoLista);
  }

  public ProductoLista updateProductoLista(Long id, ProductoLista actualizado) {
    return productoListaRepository.findById(id)
        .map(pl -> {
          pl.setProducto(actualizado.getProducto());
          pl.setCantidad(actualizado.getCantidad());
          pl.setPrecio(actualizado.getPrecioTotal());
          return productoListaRepository.save(pl);
        })
        .orElse(null);
  }

  public void deleteProductoLista(Long id) {
    productoListaRepository.deleteById(id);
  }
}
