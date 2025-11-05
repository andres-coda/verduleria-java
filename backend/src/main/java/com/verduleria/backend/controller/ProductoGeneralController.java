package com.verduleria.backend.controller;

import com.verduleria.backend.entity.ProductoGeneral;
import com.verduleria.backend.service.ProductoGeneralService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/productos-generales")
public class ProductoGeneralController {

  private final ProductoGeneralService productoGeneralService;

  public ProductoGeneralController(ProductoGeneralService productoGeneralService) {
    this.productoGeneralService = productoGeneralService;
  }

  @GetMapping
  public List<ProductoGeneral> getAllProductosGenerales() {
    return productoGeneralService.getAllProductosGenerales();
  }

  @GetMapping("/{id}")
  public ProductoGeneral getProductoGeneralById(@PathVariable Long id) {
    return productoGeneralService.getProductoGeneralById(id);
  }

  @PostMapping
  public ProductoGeneral createProductoGeneral(@RequestBody ProductoGeneral producto) {
    return productoGeneralService.createProductoGeneral(producto);
  }

  @PutMapping("/{id}")
  public ProductoGeneral updateProductoGeneral(@PathVariable Long id, @RequestBody ProductoGeneral producto) {
    return productoGeneralService.updateProductoGeneral(id, producto);
  }

  @DeleteMapping("/{id}")
  public void deleteProductoGeneral(@PathVariable Long id) {
    productoGeneralService.deleteProductoGeneral(id);
  }
}
