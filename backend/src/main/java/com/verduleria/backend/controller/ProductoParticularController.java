package com.verduleria.backend.controller;

import com.verduleria.backend.entity.ProductoParticular;
import com.verduleria.backend.service.ProductoParticularService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/productos-particulares")
public class ProductoParticularController {

  private final ProductoParticularService productoParticularService;

  public ProductoParticularController(ProductoParticularService productoParticularService) {
    this.productoParticularService = productoParticularService;
  }

  @GetMapping
  public List<ProductoParticular> getAllProductosParticulares() {
    return productoParticularService.getAllProductosParticulares();
  }

  @GetMapping("/{id}")
  public ProductoParticular getProductoParticularById(@PathVariable Long id) {
    return productoParticularService.getProductoParticularById(id);
  }

  @PostMapping
  public ProductoParticular createProductoParticular(@RequestBody ProductoParticular producto) {
    return productoParticularService.createProductoParticular(producto);
  }

  @PutMapping("/{id}")
  public ProductoParticular updateProductoParticular(@PathVariable Long id, @RequestBody ProductoParticular producto) {
    return productoParticularService.updateProductoParticular(id, producto);
  }

  @DeleteMapping("/{id}")
  public void deleteProductoParticular(@PathVariable Long id) {
    productoParticularService.deleteProductoParticular(id);
  }

  @GetMapping("/usuario/{usuarioId}")
  public List<ProductoParticular> getProductosByUsuario(@PathVariable Long usuarioId) {
    return productoParticularService.getProductosByUsuario(usuarioId);
  }
}
