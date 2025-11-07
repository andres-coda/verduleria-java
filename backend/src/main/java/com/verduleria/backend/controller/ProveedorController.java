package com.verduleria.backend.controller;

import com.verduleria.backend.dto.ProveedorDto;
import com.verduleria.backend.entity.Proveedor;
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
    
    List<ProveedorDto> proveedoresDTO = proveedores.stream()
        .map(this::convertToDTO)
        .collect(Collectors.toList());
    
    return ResponseEntity.ok(proveedoresDTO);
  }

  // Obtener un proveedor específico
  @GetMapping("/{id}")
  public ResponseEntity<ProveedorDto> getProveedorById(
      @PathVariable Long id,
      @AuthenticationPrincipal Usuario usuario) {
    
    Proveedor proveedor = proveedorService.getProveedorByIdAndUsuario(id, usuario.getId());
    return ResponseEntity.ok(convertToDTO(proveedor));
  }

  // Crear nuevo proveedor
  @PostMapping
  public ResponseEntity<ProveedorDto> createProveedor(
      @RequestBody Proveedor proveedor,
      @AuthenticationPrincipal Usuario usuario) {
    
    // Validar datos
    validateProveedor(proveedor);
    
    // Asignar usuario automáticamente
    proveedor.setUsuario(usuario);
    
    Proveedor nuevoProveedor = proveedorService.createProveedor(proveedor);
    return ResponseEntity.status(HttpStatus.CREATED).body(convertToDTO(nuevoProveedor));
  }

  // Actualizar proveedor
  @PutMapping("/{id}")
  public ResponseEntity<ProveedorDto> updateProveedor(
      @PathVariable Long id,
      @RequestBody Proveedor proveedor,
      @AuthenticationPrincipal Usuario usuario) {
    
    // Validar datos
    validateProveedor(proveedor);
    
    // Actualizar manteniendo el usuario
    Proveedor proveedorActualizado = proveedorService.updateProveedor(id, proveedor, usuario.getId());
    return ResponseEntity.ok(convertToDTO(proveedorActualizado));
  }

  // Eliminar proveedor
  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteProveedor(
      @PathVariable Long id,
      @AuthenticationPrincipal Usuario usuario) {
    
    proveedorService.deleteProveedor(id, usuario.getId());
    return ResponseEntity.noContent().build();
  }

  // Convertir entidad a DTO
  private ProveedorDto convertToDTO(Proveedor proveedor) {
    return new ProveedorDto(
        proveedor.getId(),
        proveedor.getNombre(),
        proveedor.getEmail(),
        proveedor.getTelefono(),
        proveedor.getUsuario().getId()
    );
  }

  // Validar proveedor
  private void validateProveedor(Proveedor proveedor) {
    if (proveedor.getNombre() == null || proveedor.getNombre().trim().isEmpty()) {
      throw new ValidationException("El nombre del proveedor es obligatorio");
    }

    if (proveedor.getEmail() != null && !proveedor.getEmail().trim().isEmpty()) {
      if (!isValidEmail(proveedor.getEmail())) {
        throw new ValidationException("El formato del email no es válido");
      }
    }

    if (proveedor.getTelefono() == null || proveedor.getTelefono().trim().isEmpty()) {
      throw new ValidationException("El teléfono es obligatorio");
    }
  }

  private boolean isValidEmail(String email) {
    String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
    return email.matches(emailRegex);
  }
}