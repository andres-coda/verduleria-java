package com.verduleria.backend.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class ProductoLista extends Producto {
  @ManyToOne
  @JoinColumn(name = "producto_id")
  private Producto producto;
  private int cantidad;
  private double precioTotal;

  public ProductoLista(){}

  public ProductoLista(Producto producto, int cantidad) {
    this.producto = producto;
    this.cantidad = cantidad;
  }

  public Producto getProducto() {
    return producto;
  }

  public void setProducto(Producto producto) {
    this.producto = producto;
  }

  public int getCantidad() {
    return cantidad;
  }

  public void setCantidad(int cantidad) {
    this.cantidad = cantidad;
  }

  public void setPrecio(double precio){
    this.precioTotal = precio;
  }  

  @Override
  public double getPrecio() {
    return this.getPrecioTotal()/this.getCantidad()*this.getProducto().getPorcentajeAumento();
  }

  public double getPrecioTotal() {
    return precioTotal;
  }
}
