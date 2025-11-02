package com.verduleria.backend.entity;

import java.time.LocalDate;
import java.util.ArrayList;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

public class Lista {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private LocalDate fecha;
  private Proveedor proveedor;
  private Usuario usuario;
  private ArrayList<ProductoLista> productos;

  public Lista(LocalDate fecha, Proveedor proveedor, Usuario usuario) {
    this.fecha = fecha;
    this.proveedor = proveedor;
    this.usuario = usuario;
    this.productos = new ArrayList<ProductoLista>();
  }

  public Long getId() {
    return id;
  }

  public LocalDate getFecha() {
    return fecha;
  }

  public void setFecha(LocalDate fecha) {
    this.fecha = fecha;
  }

  public Proveedor getProveedor() {
    return proveedor;
  }

  public void setProveedor(Proveedor proveedor) {
    this.proveedor = proveedor;
  }

  public Usuario getUsuario() {
    return usuario;
  }

  public ArrayList<ProductoLista> getProductos() {
    ArrayList<ProductoLista> nuevoProducto = this.productos;
    return nuevoProducto;
  }

  public void addProducto(ProductoLista producto) {
    this.productos.add(producto);
  }

  public void quitarProducto(ProductoLista producto) {
    productos.removeIf(p -> p.getId().equals(producto.getId()));
  }

}
