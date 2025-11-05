package com.verduleria.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.verduleria.backend.entity.Rubro;

@Repository
public interface RubroRepository extends JpaRepository<Rubro, Long> {
  
    Rubro findByNombre(String nombre);
    boolean existsByNombre(String nombre);
}

