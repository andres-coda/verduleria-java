package com.verduleria.backend.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.verduleria.backend.dto.UpdateProductoParticularRequest;
import com.verduleria.backend.entity.ProductoParticular;
import com.verduleria.backend.entity.Rubro;
import com.verduleria.backend.entity.RubroParticular;
import com.verduleria.backend.exception.ProductoNotFoundException;
import com.verduleria.backend.exception.UnauthorizedAccessException;
import com.verduleria.backend.repository.ProductoParticularRepository;
import com.verduleria.backend.repository.RubroRepository;

@Service
public class ProductoParticularService {

  private final ProductoParticularRepository productoParticularRepository;
  private final RubroRepository rubroRepository;

  public ProductoParticularService(ProductoParticularRepository productoParticularRepository,
  RubroRepository rubroRepository) {
    this.productoParticularRepository = productoParticularRepository;
    this.rubroRepository = rubroRepository;
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

  public ProductoParticular updateProductoParticular(Long id, UpdateProductoParticularRequest request, Long usuarioId) {
    return productoParticularRepository.findById(id)
        .map(pp -> {
          // Verificar que pertenezca al usuario
          if (!pp.getUsuario().getId().equals(usuarioId)) {
            throw new UnauthorizedAccessException("No tienes permiso para actualizar este producto");
          }

          // Actualizar campos básicos
          pp.setNombre(request.getNombre());
          pp.setPrecio(request.getPrecio());
          pp.setMedida(request.getMedida());
          pp.setPorcentajeAumento(request.getPorcentajeAumento());
          Long rubroId = request.getRubroId();

          // Actualizar rubro si se proporciona
          if (rubroId != null) {
            Rubro rubro = rubroRepository.findById(rubroId)
                .orElseThrow(() -> new RuntimeException("Rubro no encontrado"));

            // Verificar que el rubro pertenezca al usuario
            if (rubro instanceof RubroParticular) {
              RubroParticular rubroParticular = (RubroParticular) rubro;
              if (!rubroParticular.getUsuario().getId().equals(usuarioId)) {
                throw new UnauthorizedAccessException("No tienes permiso para usar este rubro");
              }
            }

            pp.setRubro(rubro);
          }

          return productoParticularRepository.save(pp);
        })
        .orElseThrow(() -> new ProductoNotFoundException("Producto no encontrado"));
  }

  public void deleteProductoParticular(Long id) {
    productoParticularRepository.deleteById(id);
  }
}
