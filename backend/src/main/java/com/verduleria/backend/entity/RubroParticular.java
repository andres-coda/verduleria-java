package com.verduleria.backend.entity;

public class RubroParticular extends Rubro {
    Usuario usuario;

    public RubroParticular(String nombre, Usuario usuario) {
      super(nombre);
      this.usuario = usuario;
    }

    public Usuario getUsuario() {      
      return usuario;
    }  
  
}
