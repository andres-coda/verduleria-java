package com.verduleria.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.verduleria.backend.entity.Proveedor;

public interface ProveedorRepository extends JpaRepository<Proveedor, Long> {
    
}
