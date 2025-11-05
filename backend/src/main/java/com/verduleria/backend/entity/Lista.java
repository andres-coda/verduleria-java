package com.verduleria.backend.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Lista {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private LocalDate fecha;

  @ManyToOne
  @JoinColumn(name = "proveedor_id")
  private Proveedor proveedor;

  @ManyToOne
  @JoinColumn(name = "usuario_id")
  private Usuario usuario;

  @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
  @JoinColumn(name = "lista_id")
  private List<ProductoLista> productos = new ArrayList<ProductoLista>();;

  public Lista() {
  }

  public Lista(LocalDate fecha, Proveedor proveedor, Usuario usuario) {
    this.fecha = fecha;
    this.proveedor = proveedor;
    this.usuario = usuario;
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

  public List<ProductoLista> getProductos() {
    List<ProductoLista> nuevoProducto = this.productos;
    return nuevoProducto;
  }

  public void addProducto(ProductoLista producto) {
    this.productos.add(producto);
  }

  public void quitarProducto(ProductoLista producto) {
    productos.removeIf(p -> p.getId().equals(producto.getId()));
  }

}
