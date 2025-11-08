package com.verduleria.backend.dto;

import java.util.List;

public class ProductoDto {
  private Long id;
  private String nombre;
  private double precio;
  private String medida;
  private double porcentajeAumento;
  private Long rubroId;
  private String rubroNombre;
  private List<ProveedorSimpleDto> proveedores;

  public ProductoDto() {
  }

  public ProductoDto(Long id, String nombre, double precio, String medida, double porcentajeAumento, 
                     Long rubroId, String rubroNombre, List<ProveedorSimpleDto> proveedores) {
    this.id = id;
    this.nombre = nombre;
    this.precio = precio;
    this.medida = medida;
    this.porcentajeAumento = porcentajeAumento;
    this.rubroId = rubroId;
    this.rubroNombre = rubroNombre;
    this.proveedores = proveedores;
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

  public Long getRubroId() {
    return rubroId;
  }

  public void setRubroId(Long rubroId) {
    this.rubroId = rubroId;
  }

  public String getRubroNombre() {
    return rubroNombre;
  }

  public void setRubroNombre(String rubroNombre) {
    this.rubroNombre = rubroNombre;
  }

  public List<ProveedorSimpleDto> getProveedores() {
    return proveedores;
  }

  public void setProveedores(List<ProveedorSimpleDto> proveedores) {
    this.proveedores = proveedores;
  }
}