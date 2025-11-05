package com.verduleria.backend.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

@Entity
public class Usuario extends Persona {
  private String password;
  @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<RubroParticular> rubrosParticulares = new ArrayList<RubroParticular>();
  @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<Proveedor> proveedores = new ArrayList<Proveedor>();
  private ArrayList<Lista> listas;

  public Usuario() {
  }

  public Usuario(String nombre, String email, String telefono, String password) {
    super(nombre, email, telefono);
    this.password = password;
    this.listas = new ArrayList<Lista>();
  }

  public String getPassword() {
    return password;
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

  public ArrayList<Lista> getListas() {
    ArrayList<Lista> aux = this.listas;
    return aux;
  }

  public void addLista(Lista lista) {
    this.listas.add(lista);
  }

  public void quitarLista(Lista lista) {
    this.listas.removeIf(l -> l.getId().equals(lista.getId()));
  }

}
