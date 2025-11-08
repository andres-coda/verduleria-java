package com.verduleria.backend.dto;

import java.util.List;

public class UpdateProductoParticularRequest {
  private String nombre;
  private Long rubroId;  // ✅ Solo el ID
  private double precio;
  private String medida;
  private double porcentajeAumento;
  private List<Long> proveedoresIds;  // Opcional: si querés actualizar proveedores también

  public UpdateProductoParticularRequest() {
  }

  // Getters y Setters
  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public Long getRubroId() {
    return rubroId;
  }

  public void setRubroId(Long rubroId) {
    this.rubroId = rubroId;
  }

  public double getPrecio() {
    return precio;
  }

  public void setPrecio(double precio) {
    this.precio = precio;
  }

  public String getMedida() {
    return medida;
  }

  public void setMedida(String medida) {
    this.medida = medida;
  }

  public double getPorcentajeAumento() {
    return porcentajeAumento;
  }

  public void setPorcentajeAumento(double porcentajeAumento) {
    this.porcentajeAumento = porcentajeAumento;
  }

  public List<Long> getProveedoresIds() {
    return proveedoresIds;
  }

  public void setProveedoresIds(List<Long> proveedoresIds) {
    this.proveedoresIds = proveedoresIds;
  }
}