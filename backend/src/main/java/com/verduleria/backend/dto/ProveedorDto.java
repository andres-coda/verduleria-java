package com.verduleria.backend.dto;

import java.util.List;

public class ProveedorDto {
  private Long id;
  private String nombre;
  private String email;
  private String telefono;
  private Long usuarioId;
  private List<ProductoDto> productos;

  public ProveedorDto() {
  }

  public ProveedorDto(Long id, String nombre, String email, String telefono, Long usuarioId, List<ProductoDto> productos) {
    this.id = id;
    this.nombre = nombre;
    this.email = email;
    this.telefono = telefono;
    this.usuarioId = usuarioId;
    this.productos = productos;
  }

  // Getters y Setters
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getTelefono() {
    return telefono;
  }

  public void setTelefono(String telefono) {
    this.telefono = telefono;
  }

  public Long getUsuarioId() {
    return usuarioId;
  }

  public void setUsuarioId(Long usuarioId) {
    this.usuarioId = usuarioId;
  }

  public List<ProductoDto> getProductos() {
    return productos;
  }

  public void setProductos(List<ProductoDto> productos) {
    this.productos = productos;
  }
}