package com.verduleria.backend.entity;

import jakarta.persistence.Entity;

@Entity
public class RubroParticular extends Rubro {
    Usuario usuario;

    public RubroParticular(){}

    public RubroParticular(String nombre, Usuario usuario) {
      super(nombre);
      this.usuario = usuario;
    }

    public Usuario getUsuario() {      
      return usuario;
    }  
  
}
