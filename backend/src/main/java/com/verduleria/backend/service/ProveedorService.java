package com.verduleria.backend.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.verduleria.backend.entity.Proveedor;
import com.verduleria.backend.exception.ProveedorNotFoundException;
import com.verduleria.backend.exception.UnauthorizedAccessException;
import com.verduleria.backend.repository.ProveedorRepository;

@Service
public class ProveedorService {
    
    private final ProveedorRepository proveedorRepository;

    public ProveedorService(ProveedorRepository proveedorRepository) {
        this.proveedorRepository = proveedorRepository;
    }

    // Obtener todos los proveedores de un usuario
    public List<Proveedor> getProveedoresByUsuario(Long usuarioId) {
        return proveedorRepository.findByUsuarioId(usuarioId);
    }

    // Obtener un proveedor por ID verificando que pertenezca al usuario
    public Proveedor getProveedorByIdAndUsuario(Long id, Long usuarioId) {
        Proveedor proveedor = proveedorRepository.findById(id)
            .orElseThrow(() -> new ProveedorNotFoundException("Proveedor no encontrado"));
        
        // Verificar que el proveedor pertenezca al usuario
        if (!proveedor.getUsuario().getId().equals(usuarioId)) {
            throw new UnauthorizedAccessException("No tienes permiso para acceder a este proveedor");
        }
        
        return proveedor;
    }

    // Crear proveedor (ya viene con el usuario asignado desde el controller)
    public Proveedor createProveedor(Proveedor proveedor) {
        return proveedorRepository.save(proveedor);
    }

    // Actualizar proveedor verificando permisos
    public Proveedor updateProveedor(Long id, Proveedor proveedorActualizado, Long usuarioId) {
        Proveedor proveedor = getProveedorByIdAndUsuario(id, usuarioId);
        
        proveedor.setNombre(proveedorActualizado.getNombre());
        proveedor.setEmail(proveedorActualizado.getEmail());
        proveedor.setTelefono(proveedorActualizado.getTelefono());
        
        return proveedorRepository.save(proveedor);
    }

    // Eliminar proveedor verificando permisos
    public void deleteProveedor(Long id, Long usuarioId) {
        Proveedor proveedor = getProveedorByIdAndUsuario(id, usuarioId);
        proveedorRepository.delete(proveedor);
    }
}