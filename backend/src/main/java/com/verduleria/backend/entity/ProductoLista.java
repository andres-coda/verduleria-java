package com.verduleria.backend.entity;

public class ProductoLista extends Producto {
  private Producto producto;
  private int cantidad;
  private double precioTotal;

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
