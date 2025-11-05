package com.verduleria.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.verduleria.backend.entity.ProductoParticular;

@Repository
public interface ProductoParticularRepository extends JpaRepository<ProductoParticular, Long> {
    
}
