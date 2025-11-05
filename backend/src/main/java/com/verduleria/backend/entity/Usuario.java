package com.verduleria.backend.entity;

import java.util.ArrayList;

import jakarta.persistence.Entity;

@Entity
public class Usuario extends Persona{
  private String password;
  private ArrayList<Rubro> rubros;
  private ArrayList<Proveedor> proveedores;
  private ArrayList<Lista> listas;

  public Usuario(){}
  
  public Usuario(String nombre, String email, String telefono, String password) {
    super(nombre, email, telefono);
    this.password = password;
    this.rubros = new ArrayList<Rubro>();
    this.proveedores = new ArrayList<Proveedor>();
    this.listas = new ArrayList<Lista>();
  }


  public String getPassword() {
    return password;
  }


  public void setPassword(String password) {
    this.password = password;
  }

  public ArrayList<Rubro> getRubros() {
    ArrayList<Rubro> aux = this.rubros;
    return aux;
  }

  public void addRubro(Rubro rubro) {
    this.rubros.add(rubro);
  }

  public void quitarRubro(Rubro rubro){
    this.rubros.removeIf(r -> r.getId().equals(rubro.getId()));
  }


  public ArrayList<Proveedor> getProveedores() {
    ArrayList<Proveedor> aux = this.proveedores;
    return aux;
  }


  public void addProveedor(Proveedor proveedor) {
    this.proveedores.add(proveedor);
  }

   public void quitarProveedor(Proveedor proveedor){
    this.proveedores.removeIf(p -> p.getId().equals(proveedor.getId()));
  }

  public ArrayList<Lista> getListas() {
    ArrayList<Lista> aux = this.listas;
    return aux;
  }


  public void addLista(Lista lista) {
    this.listas.add(lista);
  }

  public void quitarLista(Lista lista){
    this.listas.removeIf(l -> l.getId().equals(lista.getId()));
  }

}
