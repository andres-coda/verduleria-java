package com.verduleria.backend.entity;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;

@Entity
public class Proveedor extends Persona {
  
  private String email;
  
  @ManyToOne
  @JoinColumn(name = "usuario_id")
  @JsonIgnore
  private Usuario usuario;
  
  @ManyToMany(mappedBy = "proveedores")
  @JsonIgnore
  private List<ProductoParticular> productos = new ArrayList<>();

  public Proveedor() {
  }

  public Proveedor(String nombre, String email, String telefono) {
    super(nombre, telefono);
    this.email = email;
  }

  public Usuario getUsuario() {
    return usuario;
  }

  public void setUsuario(Usuario usuario) {
    this.usuario = usuario;
  }

  public List<ProductoParticular> getProductos() {
    return new ArrayList<>(this.productos);
  }

  public void addProducto(ProductoParticular producto) {
    if (!this.productos.contains(producto)) {
      this.productos.add(producto);
      producto.addProveedor(this);
    }
  }

  public void removeProducto(ProductoParticular producto) {
    this.productos.remove(producto);
    producto.removeProveedor(this);
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }
}