package com.verduleria.backend.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Proveedor extends Persona {
  private String email;
  @ManyToOne
  @JoinColumn(name = "usuario_id")
  private Usuario usuario;
  @OneToMany(mappedBy = "proveedor", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<ProductoParticular> productos = new ArrayList<ProductoParticular>();

  public Proveedor() {
  }

  public Proveedor(String nombre, String email, String telefono, Usuario usuario) {
    super(nombre, telefono);
    this.email = email;
    this.usuario = usuario;
  }

  public Usuario getUsuario() {
    return usuario;
  }

  public List<ProductoParticular> getProductos() {
    List<ProductoParticular> nuevoProducto = this.productos;
    return nuevoProducto;
  }

  public void addProducto(ProductoParticular producto) {
    this.productos.add(producto);
  }

  public void quitarProducto(ProductoParticular producto) {
    productos.removeIf(p -> p.getId().equals(producto.getId()));
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

}
