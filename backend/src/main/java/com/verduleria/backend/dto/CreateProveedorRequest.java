package com.verduleria.backend.dto;

import java.util.List;

public class CreateProveedorRequest {
  private String nombre;
  private String telefono;
  private String email;
  private List<Long> productos; // IDs de productos existentes

  public CreateProveedorRequest() {
  }

  public CreateProveedorRequest(String nombre, String telefono, String email, List<Long> productos) {
    this.nombre = nombre;
    this.telefono = telefono;
    this.email = email;
    this.productos = productos;
  }

  // Getters y Setters
  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public String getTelefono() {
    return telefono;
  }

  public void setTelefono(String telefono) {
    this.telefono = telefono;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public List<Long> getProductos() {
    return productos;
  }

  public void setProductos(List<Long> productos) {
    this.productos = productos;
  }
}