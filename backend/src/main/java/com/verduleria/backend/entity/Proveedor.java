package com.verduleria.backend.entity;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
  @JsonIgnore  // ✅ Evita loops infinitos
  private Usuario usuario;
  
  @OneToMany(mappedBy = "proveedor", cascade = CascadeType.ALL, orphanRemoval = true)
  @JsonIgnore  // ✅ Evita loops infinitos
  private List<ProductoParticular> productos = new ArrayList<ProductoParticular>();

  public Proveedor() {
  }

  public Proveedor(String nombre, String email, String telefono) {
    super(nombre, telefono);
    this.email = email;
  }

  public Usuario getUsuario() {
    return usuario;
  }

  public List<ProductoParticular> getProductos() {
    return new ArrayList<>(this.productos);
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

  public void setUsuario(Usuario usuario) {
    this.usuario = usuario;
  }
}
