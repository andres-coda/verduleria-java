package com.verduleria.backend.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Proveedor extends Persona {
    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    public Proveedor() {
    }

    public Proveedor(String nombre, String email, String telefono, Usuario usuario) {
        super(nombre, email, telefono);
        this.usuario = usuario;
    }

    public Usuario getUsuario() {
        return usuario;
    }

}
