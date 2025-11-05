package com.verduleria.backend.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class RubroParticular extends Rubro {
  @ManyToOne
  @JoinColumn(name = "usuario_id")
  private Usuario usuario;

  public RubroParticular() {
  }

  public RubroParticular(String nombre, Usuario usuario) {
    super(nombre);
    this.usuario = usuario;
  }

  public Usuario getUsuario() {
    return usuario;
  }

}
