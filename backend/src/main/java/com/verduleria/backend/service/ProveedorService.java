package com.verduleria.backend.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.verduleria.backend.entity.Proveedor;
import com.verduleria.backend.repository.ProveedorRepository;

@Service
public class ProveedorService {

    private final ProveedorRepository proveedorRepository;

    public ProveedorService(ProveedorRepository proveedorRepository) {
        this.proveedorRepository = proveedorRepository;
    }

    public List<Proveedor> getAllProveedores() {
        return proveedorRepository.findAll();
    }

    public List<Proveedor> getProveedoresByUsuario(Long usuarioId) {
        return proveedorRepository.findByUsuarioId(usuarioId);
    }

    public Proveedor getProveedorById(Long id) {
        return proveedorRepository.findById(id).orElse(null);
    }

    public Proveedor createProveedor(Proveedor proveedor) {
        return proveedorRepository.save(proveedor);
    }

    public Proveedor updateProveedor(Long id, Proveedor proveedorActualizado) {
        return proveedorRepository.findById(id)
            .map(proveedor -> {
                proveedor.setNombre(proveedorActualizado.getNombre());
                proveedor.setEmail(proveedorActualizado.getEmail());
                proveedor.setTelefono(proveedorActualizado.getTelefono());
                return proveedorRepository.save(proveedor);
            })
            .orElse(null);
    }

    public void deleteProveedor(Long id) {
        proveedorRepository.deleteById(id);
    }
}

