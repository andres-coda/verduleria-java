package com.verduleria.backend.entity;

import jakarta.persistence.Entity;

@Entity
public class ProductoGeneral extends Producto{
  private String nombre;
  private Rubro rubro;

  public ProductoGeneral(){}

    public ProductoGeneral(String nombre, Rubro rubro){
        super();
        this.nombre = nombre;
        this.rubro = rubro;
    }
    

  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public Rubro getRubro() {
    return rubro;
  }

  public void setRubro(Rubro rubro) {
    this.rubro = rubro;
  }

  public double getPrecio(){
    return 0;
  }
}
