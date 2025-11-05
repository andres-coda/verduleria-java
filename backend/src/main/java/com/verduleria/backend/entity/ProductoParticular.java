package com.verduleria.backend.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class ProductoParticular extends ProductoGeneral {
  @ManyToOne
  @JoinColumn(name = "usuario_id")
  private Usuario usuario;

  @ManyToOne
  @JoinColumn(name = "proveedor_id")
  private Proveedor proveedor;

  private double precio;
  private String medida;
  private double porcentajeAumento;

  public ProductoParticular() {
  }

  public ProductoParticular(String nombre, Rubro rubro, Usuario usuario, double precio, String medida,
      double porcentaje) {
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
