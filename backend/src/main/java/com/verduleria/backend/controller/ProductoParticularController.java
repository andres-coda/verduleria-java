package com.verduleria.backend.controller;

import com.verduleria.backend.dto.ProductoDto;
import com.verduleria.backend.dto.ProveedorSimpleDto;
import com.verduleria.backend.dto.UpdateProductoParticularRequest;
import com.verduleria.backend.entity.ProductoParticular;
import com.verduleria.backend.entity.Usuario;
import com.verduleria.backend.service.ProductoParticularService;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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

  private ProductoDto convertToDto(ProductoParticular producto) {
  ProductoDto dto = new ProductoDto();
  dto.setId(producto.getId());
  dto.setNombre(producto.getNombre());
  dto.setPrecio(producto.getPrecio());
  dto.setMedida(producto.getMedida());
  dto.setPorcentajeAumento(producto.getPorcentajeAumento());

  if (producto.getRubro() != null) {
    dto.setRubroId(producto.getRubro().getId());
    dto.setRubroNombre(producto.getRubro().getNombre());
  }

  if (producto.getProveedores() != null) {
    dto.setProveedores(
        producto.getProveedores().stream()
            .map(p -> new ProveedorSimpleDto(p.getId(), p.getNombre(),p.getTelefono(), p.getEmail()))
            .toList()
    );
  }

  return dto;
}

  @PutMapping("/{id}")
  public ResponseEntity<ProductoDto> updateProductoParticular(
      @PathVariable Long id,
      @RequestBody UpdateProductoParticularRequest request,
      @AuthenticationPrincipal Usuario usuario) {

    ProductoParticular actualizado = productoParticularService.updateProductoParticular(
        id, request, usuario.getId());

    return ResponseEntity.ok(convertToDto(actualizado));
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
