package com.verduleria.backend.entity;

public class Proveedor extends Persona {
    Usuario usuario;

    public Proveedor(String nombre, String email, String telefono, Usuario usuario) {
        super(nombre, email, telefono);
        this.usuario = usuario;
    }

    public Usuario getUsuario() {
        return usuario;
    }
    
}
