package com.verduleria.backend.entity;

import jakarta.persistence.Entity;

@Entity
public class Proveedor extends Persona {
    Usuario usuario;

    public Proveedor(){}

    public Proveedor(String nombre, String email, String telefono, Usuario usuario) {
        super(nombre, email, telefono);
        this.usuario = usuario;
    }

    public Usuario getUsuario() {
        return usuario;
    }
    
}
