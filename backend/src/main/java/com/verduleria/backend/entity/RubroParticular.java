package com.verduleria.backend.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;


@Entity
@DiscriminatorValue("PARTICULAR")
public class RubroParticular extends Rubro {
  @ManyToOne
   @JsonIgnore
  @JoinColumn(name = "usuario_id")
  private Usuario usuario;

  public RubroParticular() {
  }

  public RubroParticular(String nombre) {
    super(nombre);
  }

  public Usuario getUsuario() {
    return usuario;
  }

  public void setUsuario(Usuario usuario) {
    this.usuario = usuario;
  }

  

}
