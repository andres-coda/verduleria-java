package com.verduleria.backend.service;

import com.verduleria.backend.entity.Usuario;
import com.verduleria.backend.repository.UsuarioRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService implements UserDetailsService {
  
  private final UsuarioRepository usuarioRepository;
  private final PasswordEncoder passwordEncoder;

  public UsuarioService(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
    this.usuarioRepository = usuarioRepository;
    this.passwordEncoder = passwordEncoder;
  }
  
  @Override
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    return usuarioRepository.findByEmail(email)
        .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado con email: " + email));
  }
  
  public List<Usuario> getAllUsuarios() {
    return usuarioRepository.findAll();
  }
  
  public Usuario getUsuarioById(Long id) {
    return usuarioRepository.findById(id).orElse(null);
  }
  
  public Usuario createUsuario(Usuario usuario) {
    // Encriptar password antes de guardar
    usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
    return usuarioRepository.save(usuario);
  }
  
  public Usuario updateUsuario(Long id, Usuario usuario) {
    Usuario existente = usuarioRepository.findById(id).orElse(null);
    if (existente != null) {
      existente.setNombre(usuario.getNombre());
      existente.setEmail(usuario.getEmail());
      existente.setTelefono(usuario.getTelefono());
      if (usuario.getPassword() != null && !usuario.getPassword().isEmpty()) {
        existente.setPassword(passwordEncoder.encode(usuario.getPassword()));
      }
      return usuarioRepository.save(existente);
    }
    return null;
  }
  
  public void deleteUsuario(Long id) {
    usuarioRepository.deleteById(id);
  }
}