package com.verduleria.backend.entity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

@Entity
public class Usuario extends Persona implements UserDetails {
  @Column(unique = true)
  private String email;
  private String password;
  @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
  @JsonIgnore
  private List<RubroParticular> rubrosParticulares = new ArrayList<RubroParticular>();
  @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
  @JsonIgnore
  private List<Proveedor> proveedores = new ArrayList<Proveedor>();
  @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
  @JsonIgnore
  private List<ProductoParticular> productos = new ArrayList<ProductoParticular>();
  @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
  @JsonIgnore
  private List<Lista> listas = new ArrayList<Lista>();

  public Usuario() {
  }

  public Usuario(String nombre, String email, String telefono, String password) {
    super(nombre, telefono);
    this.email = email;
    this.password = password;
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return Collections.emptyList();
  }

  @Override
  public String getUsername() {
    return email;
  }

  @Override
  public String getPassword() {
    return password;
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public List<RubroParticular> getRubros() {
    List<RubroParticular> aux = this.rubrosParticulares;
    return aux;
  }

  public void addRubro(RubroParticular rubro) {
    this.rubrosParticulares.add(rubro);
  }

  public void quitarRubro(Rubro rubro) {
    this.rubrosParticulares.removeIf(r -> r.getId().equals(rubro.getId()));
  }

  public List<Proveedor> getProveedores() {
    List<Proveedor> aux = this.proveedores;
    return aux;
  }

  public void addProveedor(Proveedor proveedor) {
    this.proveedores.add(proveedor);
  }

  public void quitarProveedor(Proveedor proveedor) {
    this.proveedores.removeIf(p -> p.getId().equals(proveedor.getId()));
  }

  public List<Lista> getListas() {
    List<Lista> aux = this.listas;
    return aux;
  }

  public void addLista(Lista lista) {
    this.listas.add(lista);
  }

  public void quitarLista(Lista lista) {
    this.listas.removeIf(l -> l.getId().equals(lista.getId()));
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
