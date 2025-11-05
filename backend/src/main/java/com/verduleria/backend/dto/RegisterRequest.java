package com.verduleria.backend.dto;

public class RegisterRequest {
  private String nombre;
  private String email;
  private String telefono;
  private String password;

  public RegisterRequest() {
  }

  public RegisterRequest(String nombre, String email, String telefono, String password) {
    this.nombre = nombre;
    this.email = email;
    this.telefono = telefono;
    this.password = password;
  }

  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getTelefono() {
    return telefono;
  }

  public void setTelefono(String telefono) {
    this.telefono = telefono;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }
}