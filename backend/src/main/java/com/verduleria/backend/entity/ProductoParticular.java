package com.verduleria.backend.entity;

public class ProductoParticular extends ProductoGeneral {
  private Usuario usuario;
  private double precio;
  private String medida;
  private double porcentajeAumento;

  public ProductoParticular(String nombre, Rubro rubro, Usuario usuario, double precio, String medida, double porcentaje) {
    super(nombre, rubro);
    this.usuario = usuario;
    this.precio = precio;
    this.medida = medida;
    this.porcentajeAumento = porcentaje;
  }

  public Usuario getUsuario() {
    return usuario;
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

  @Override
  public double getPorcentajeAumento() {
    return porcentajeAumento;
  }

  public void setPorcentajeAumento(double porcentajeAumento) {
    this.porcentajeAumento = porcentajeAumento;
  }  
}
