package com.verduleria.backend.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.verduleria.backend.entity.Proveedor;
import com.verduleria.backend.entity.Usuario;

@Repository
public interface ProveedorRepository extends JpaRepository<Proveedor, Long> {

     List<Proveedor> findByUsuario(Usuario usuario);
     List<Proveedor> findByUsuarioId(Long usuarioId);

    Proveedor findByNombreAndUsuario(String nombre, Usuario usuario);
}
