package com.verduleria.backend.entity;

import java.util.ArrayList;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class Persona {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String email;
    private String telefono;
    private ArrayList<Producto> productos;

    protected Persona(){}

    protected Persona(String nombre, String email, String telefono) {
        this.nombre = nombre;
        this.email = email;
        this.telefono = telefono;
        this.productos = new ArrayList<Producto>();
    }

    public Long getId() {
        return id;
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

    public ArrayList<Producto> getProductos() {
        ArrayList<Producto> nuevoProducto = this.productos;
        return nuevoProducto;
    }

    public void addProducto(Producto producto) {
        this.productos.add(producto);
    }

    public void quitarProducto(Producto producto) {
        productos.removeIf(p -> p.getId().equals(producto.getId()));
    }

}
