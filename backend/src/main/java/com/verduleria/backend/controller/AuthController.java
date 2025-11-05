package com.verduleria.backend.controller;

import com.verduleria.backend.dto.AuthResponse;
import com.verduleria.backend.dto.LoginRequest;
import com.verduleria.backend.dto.RegisterRequest;
import com.verduleria.backend.entity.Usuario;
import com.verduleria.backend.repository.UsuarioRepository;
import com.verduleria.backend.security.JwtService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
  
  private final UsuarioRepository usuarioRepository;
  private final PasswordEncoder passwordEncoder;
  private final JwtService jwtService;
  private final AuthenticationManager authenticationManager;

  public AuthController(
      UsuarioRepository usuarioRepository,
      PasswordEncoder passwordEncoder,
      JwtService jwtService,
      AuthenticationManager authenticationManager) {
    this.usuarioRepository = usuarioRepository;
    this.passwordEncoder = passwordEncoder;
    this.jwtService = jwtService;
    this.authenticationManager = authenticationManager;
  }
  
  @PostMapping("/register")
  public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest request) {
    // Verificar si el email ya existe
    if (usuarioRepository.findByEmail(request.getEmail()).isPresent()) {
      return ResponseEntity.badRequest().build();
    }
    
    // Crear nuevo usuario
    Usuario usuario = new Usuario(
        request.getNombre(),
        request.getEmail(),
        request.getTelefono(),
        passwordEncoder.encode(request.getPassword())
    );
    
    usuarioRepository.save(usuario);
    
    // Generar token
    String token = jwtService.generateToken(usuario);
    
    return ResponseEntity.ok(new AuthResponse(token, usuario.getId(), usuario.getNombre(), usuario.getEmail()));
  }
  
  @PostMapping("/login")
  public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request) {
    // Autenticar
    authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
    );
    
    // Obtener usuario
    Usuario usuario = usuarioRepository.findByEmail(request.getEmail())
        .orElseThrow();
    
    // Generar token
    String token = jwtService.generateToken(usuario);
    
    return ResponseEntity.ok(new AuthResponse(token, usuario.getId(), usuario.getNombre(), usuario.getEmail()));
  }
}