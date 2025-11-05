package com.verduleria.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.verduleria.backend.entity.Producto;
import com.verduleria.backend.entity.ProductoLista;

@Repository
public interface ProductoListaRepository extends JpaRepository<ProductoLista, Long>  {
    Producto findByProducto(Producto producto);
}
