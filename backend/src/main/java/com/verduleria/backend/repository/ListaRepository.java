package com.verduleria.backend.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.verduleria.backend.entity.Lista;
import com.verduleria.backend.entity.Usuario;

@Repository
public interface ListaRepository extends JpaRepository<Lista, Long> {
    List<Lista> findByUsuario(Usuario usuario);

    List<Lista> findByUsuarioId(Long usuarioId);

    List<Lista> findByUsuarioAndProveedor(Long usuarioId, Long proveedorId);

    List<Lista> findByUsuarioAndFecha(Long usuarioId, LocalDate fecha);
}
