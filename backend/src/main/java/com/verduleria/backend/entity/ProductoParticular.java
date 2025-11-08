package com.verduleria.backend.entity;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class ProductoParticular extends ProductoGeneral {
  
  @ManyToOne
  @JoinColumn(name = "usuario_id")
  @JsonIgnore
  private Usuario usuario;
  
  @ManyToMany
  @JoinTable(
    name = "producto_proveedor",
    joinColumns = @JoinColumn(name = "producto_id"),
    inverseJoinColumns = @JoinColumn(name = "proveedor_id")
  )
  @JsonIgnore
  private List<Proveedor> proveedores = new ArrayList<>();
  
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

  public void setUsuario(Usuario usuario) {
    this.usuario = usuario;
  }

  public List<Proveedor> getProveedores() {
    return new ArrayList<>(proveedores);
  }

  public void setProveedores(List<Proveedor> proveedores) {
    this.proveedores = proveedores;
  }

  public void addProveedor(Proveedor proveedor) {
    if (!this.proveedores.contains(proveedor)) {
      this.proveedores.add(proveedor);
    }
  }

  public void removeProveedor(Proveedor proveedor) {
    this.proveedores.remove(proveedor);
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