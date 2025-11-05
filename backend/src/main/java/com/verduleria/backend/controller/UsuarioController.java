package com.verduleria.backend.controller;

import com.verduleria.backend.entity.Usuario;
import com.verduleria.backend.service.UsuarioService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

  private final UsuarioService usuarioService;

  public UsuarioController(UsuarioService usuarioService) {
    this.usuarioService = usuarioService;
  }

  @GetMapping
  public List<Usuario> getAllUsuarios() {
    return usuarioService.getAllUsuarios();
  }

  @GetMapping("/{id}")
  public Usuario getUsuarioById(@PathVariable Long id) {
    return usuarioService.getUsuarioById(id);
  }

  @PostMapping
  public Usuario createUsuario(@RequestBody Usuario usuario) {
    return usuarioService.createUsuario(usuario);
  }

  @PutMapping("/{id}")
  public Usuario updateUsuario(@PathVariable Long id, @RequestBody Usuario usuario) {
    return usuarioService.updateUsuario(id, usuario);
  }

  @DeleteMapping("/{id}")
  public void deleteUsuario(@PathVariable Long id) {
    usuarioService.deleteUsuario(id);
  }
}
