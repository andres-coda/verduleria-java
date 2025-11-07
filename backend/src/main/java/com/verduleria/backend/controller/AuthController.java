package com.verduleria.backend.controller;

import com.verduleria.backend.dto.AuthResponse;
import com.verduleria.backend.dto.LoginRequest;
import com.verduleria.backend.dto.RegisterRequest;
import com.verduleria.backend.entity.Usuario;
import com.verduleria.backend.exception.EmailAlreadyExistsException;
import com.verduleria.backend.exception.ValidationException;
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
    // Validaciones
    validateRegisterRequest(request);
    
    // Verificar si el email ya existe
    if (usuarioRepository.findByEmail(request.getEmail()).isPresent()) {
      throw new EmailAlreadyExistsException("El email ya está registrado");
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
    // Validaciones
    validateLoginRequest(request);
    
    // Autenticar (aquí se lanza BadCredentialsException si las credenciales son incorrectas)
    authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
    );
    
    // Obtener usuario
    Usuario usuario = usuarioRepository.findByEmail(request.getEmail())
        .orElseThrow(() -> new RuntimeException("Error al obtener el usuario"));
    
    // Generar token
    String token = jwtService.generateToken(usuario);
    
    return ResponseEntity.ok(new AuthResponse(token, usuario.getId(), usuario.getNombre(), usuario.getEmail()));
  }

  // Métodos de validación
  private void validateRegisterRequest(RegisterRequest request) {
    if (request.getNombre() == null || request.getNombre().trim().isEmpty()) {
      throw new ValidationException("El nombre es obligatorio");
    }
    
    if (request.getEmail() == null || request.getEmail().trim().isEmpty()) {
      throw new ValidationException("El email es obligatorio");
    }
    
    if (!isValidEmail(request.getEmail())) {
      throw new ValidationException("El formato del email no es válido");
    }
    
    if (request.getPassword() == null || request.getPassword().isEmpty()) {
      throw new ValidationException("La contraseña es obligatoria");
    }
    
    if (request.getPassword().length() < 6) {
      throw new ValidationException("La contraseña debe tener al menos 6 caracteres");
    }
  }

  private void validateLoginRequest(LoginRequest request) {
    if (request.getEmail() == null || request.getEmail().trim().isEmpty()) {
      throw new ValidationException("El email es obligatorio");
    }
    
    if (request.getPassword() == null || request.getPassword().isEmpty()) {
      throw new ValidationException("La contraseña es obligatoria");
    }
  }

  private boolean isValidEmail(String email) {
    String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
    return email.matches(emailRegex);
  }
}