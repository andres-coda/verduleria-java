package com.verduleria.backend.controller;

import com.verduleria.backend.dto.CreateProveedorRequest;
import com.verduleria.backend.dto.ProductoDto;
import com.verduleria.backend.dto.ProveedorDto;
import com.verduleria.backend.dto.ProveedorSimpleDto;
import com.verduleria.backend.entity.Proveedor;
import com.verduleria.backend.entity.ProductoParticular;
import com.verduleria.backend.entity.Usuario;
import com.verduleria.backend.exception.ValidationException;
import com.verduleria.backend.service.ProveedorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/proveedores")
public class ProveedorController {

  private final ProveedorService proveedorService;

  public ProveedorController(ProveedorService proveedorService) {
    this.proveedorService = proveedorService;
  }

  // Obtener todos los proveedores del usuario autenticado
  @GetMapping
  public ResponseEntity<List<ProveedorDto>> getProveedoresByUsuario(
      @AuthenticationPrincipal Usuario usuario) {
    
    List<Proveedor> proveedores = proveedorService.getProveedoresByUsuario(usuario.getId());
    
    List<ProveedorDto> proveedoresDto = proveedores.stream()
        .map(this::convertToDto)
        .collect(Collectors.toList());
    
    return ResponseEntity.ok(proveedoresDto);
  }

  // Obtener un proveedor específico
  @GetMapping("/{id}")
  public ResponseEntity<ProveedorDto> getProveedorById(
      @PathVariable Long id,
      @AuthenticationPrincipal Usuario usuario) {
    
    Proveedor proveedor = proveedorService.getProveedorByIdAndUsuario(id, usuario.getId());
    return ResponseEntity.ok(convertToDto(proveedor));
  }

  // Crear nuevo proveedor con productos
  @PostMapping
  public ResponseEntity<ProveedorDto> createProveedor(
      @RequestBody CreateProveedorRequest request,
      @AuthenticationPrincipal Usuario usuario) {
    
    // Validar datos
    validateProveedorRequest(request);
    
    // Crear entidad proveedor
    Proveedor proveedor = new Proveedor(
        request.getNombre(),
        request.getEmail(),
        request.getTelefono()
    );
    proveedor.setUsuario(usuario);
    
    // Crear proveedor con productos
    Proveedor nuevoProveedor = proveedorService.createProveedorConProductos(
        proveedor, 
        request.getProductos(), 
        usuario.getId()
    );
    
    return ResponseEntity.status(HttpStatus.CREATED).body(convertToDto(nuevoProveedor));
  }

  // Actualizar proveedor con productos
  @PutMapping("/{id}")
  public ResponseEntity<ProveedorDto> updateProveedor(
      @PathVariable Long id,
      @RequestBody CreateProveedorRequest request,
      @AuthenticationPrincipal Usuario usuario) {
    
    // Validar datos
    validateProveedorRequest(request);
    
    // Crear entidad proveedor con datos actualizados
    Proveedor proveedor = new Proveedor(
        request.getNombre(),
        request.getEmail(),
        request.getTelefono()
    );
    
    // Actualizar proveedor con productos
    Proveedor proveedorActualizado = proveedorService.updateProveedorConProductos(
        id, 
        proveedor, 
        request.getProductos(), 
        usuario.getId()
    );
    
    return ResponseEntity.ok(convertToDto(proveedorActualizado));
  }

  // Eliminar proveedor
  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteProveedor(
      @PathVariable Long id,
      @AuthenticationPrincipal Usuario usuario) {
    
    proveedorService.deleteProveedor(id, usuario.getId());
    return ResponseEntity.noContent().build();
  }

  // Convertir entidad a Dto
  private ProveedorDto convertToDto(Proveedor proveedor) {
    List<ProductoDto> productosDto = proveedor.getProductos().stream()
        .map(this::convertProductoToDto)
        .collect(Collectors.toList());
    
    return new ProveedorDto(
        proveedor.getId(),
        proveedor.getNombre(),
        proveedor.getEmail(),
        proveedor.getTelefono(),
        proveedor.getUsuario().getId(),
        productosDto
    );
  }

  // Mantener todo igual, solo actualizar este método:

private ProductoDto convertProductoToDto(ProductoParticular producto) {
    List<ProveedorSimpleDto> proveedoresDto = producto.getProveedores().stream()
        .map(p -> new ProveedorSimpleDto(p.getId(), p.getNombre(), p.getTelefono(), p.getEmail()))
        .collect(Collectors.toList());
    
    return new ProductoDto(
        producto.getId(),
        producto.getNombre(),
        producto.getPrecio(),
        producto.getMedida(),
        producto.getPorcentajeAumento(),
        producto.getRubro().getId(),
        producto.getRubro().getNombre(),
        proveedoresDto
    );
}
  // Validar proveedor
  private void validateProveedorRequest(CreateProveedorRequest request) {
    if (request.getNombre() == null || request.getNombre().trim().isEmpty()) {
      throw new ValidationException("El nombre del proveedor es obligatorio");
    }

    if (request.getEmail() != null && !request.getEmail().trim().isEmpty()) {
      if (!isValidEmail(request.getEmail())) {
        throw new ValidationException("El formato del email no es válido");
      }
    }

    if (request.getTelefono() == null || request.getTelefono().trim().isEmpty()) {
      throw new ValidationException("El teléfono es obligatorio");
    }
  }

  private boolean isValidEmail(String email) {
    String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
    return email.matches(emailRegex);
  }
}