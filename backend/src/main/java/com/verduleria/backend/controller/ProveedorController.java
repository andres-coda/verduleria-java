package com.verduleria.backend.controller;

import com.verduleria.backend.entity.Proveedor;
import com.verduleria.backend.service.ProveedorService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/proveedores")
public class ProveedorController {

  private final ProveedorService proveedorService;

  public ProveedorController(ProveedorService proveedorService) {
    this.proveedorService = proveedorService;
  }

  @GetMapping
  public List<Proveedor> getAllProveedores() {
    return proveedorService.getAllProveedores();
  }

  @GetMapping("/{id}")
  public Proveedor getProveedorById(@PathVariable Long id) {
    return proveedorService.getProveedorById(id);
  }

  @PostMapping
  public Proveedor createProveedor(@RequestBody Proveedor proveedor) {
    return proveedorService.createProveedor(proveedor);
  }

  @PutMapping("/{id}")
  public Proveedor updateProveedor(@PathVariable Long id, @RequestBody Proveedor proveedor) {
    return proveedorService.updateProveedor(id, proveedor);
  }

  @DeleteMapping("/{id}")
  public void deleteProveedor(@PathVariable Long id) {
    proveedorService.deleteProveedor(id);
  }

  @GetMapping("/usuario/{usuarioId}")
  public List<Proveedor> getProveedoresByUsuario(@PathVariable Long usuarioId) {
    return proveedorService.getProveedoresByUsuario(usuarioId);
  }
}
