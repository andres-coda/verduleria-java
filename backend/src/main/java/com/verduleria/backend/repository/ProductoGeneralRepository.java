package com.verduleria.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.verduleria.backend.entity.ProductoGeneral;
import com.verduleria.backend.entity.Rubro;

@Repository
public interface ProductoGeneralRepository extends JpaRepository<ProductoGeneral, Long> {
  List<ProductoGeneral> findByRubro(Rubro rubro);

  List<ProductoGeneral> findByNombre(String nombre);

  boolean existsByNombre(String nombre);
}
