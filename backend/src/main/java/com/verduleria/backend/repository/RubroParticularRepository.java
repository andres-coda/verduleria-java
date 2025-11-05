package com.verduleria.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.verduleria.backend.entity.RubroParticular;
import com.verduleria.backend.entity.Usuario;

@Repository
public interface RubroParticularRepository extends JpaRepository<RubroParticular, Long> {

  List<RubroParticular> findByUsuario(Usuario usuario);

  List<RubroParticular> findByUsuarioId(Long usuarioId);

  RubroParticular findByNombreAndUsuario(String nombre, Usuario usuario);

  boolean existsByNombreAndUsuario(String nombre, Usuario usuario);
}
