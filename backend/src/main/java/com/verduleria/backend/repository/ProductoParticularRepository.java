package com.verduleria.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.verduleria.backend.entity.ProductoParticular;
import com.verduleria.backend.entity.Rubro;
import com.verduleria.backend.entity.Usuario;

@Repository
public interface ProductoParticularRepository extends JpaRepository<ProductoParticular, Long> {
    List<ProductoParticular> findByUsuario(Usuario usuario);

    List<ProductoParticular> findByUsuarioId(Long usuarioId);

    List<ProductoParticular> findByUsuarioAndRubro(Usuario usuario, Rubro rubro);

    ProductoParticular findByNombreAndUsuario(String nombre, Usuario usuario);

    boolean existsByNombreAndUsuario(String nombre, Usuario usuario);
}
