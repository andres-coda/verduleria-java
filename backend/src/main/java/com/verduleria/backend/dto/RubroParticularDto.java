package com.verduleria.backend.dto;

public class RubroParticularDto {
  private Long id;
  private String nombre;

  public RubroParticularDto() {
  }

  public RubroParticularDto(Long id, String nombre) {
    this.id = id;
    this.nombre = nombre;
  }

  // Getters y Setters
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }
}
