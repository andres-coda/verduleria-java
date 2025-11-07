package com.verduleria.backend.exception;

import com.verduleria.backend.dto.ErrorResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

  // Email ya registrado
  @ExceptionHandler(EmailAlreadyExistsException.class)
  public ResponseEntity<ErrorResponse> handleEmailAlreadyExists(
      EmailAlreadyExistsException ex,
      HttpServletRequest request) {

    ErrorResponse error = new ErrorResponse(
        HttpStatus.CONFLICT.value(),
        "Conflict",
        ex.getMessage(),
        request.getRequestURI());

    return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
  }

  // Credenciales inválidas
  @ExceptionHandler(BadCredentialsException.class)
  public ResponseEntity<ErrorResponse> handleBadCredentials(
      BadCredentialsException ex,
      HttpServletRequest request) {

    ErrorResponse error = new ErrorResponse(
        HttpStatus.UNAUTHORIZED.value(),
        "Unauthorized",
        "Email o contraseña incorrectos",
        request.getRequestURI());

    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(error);
  }

  // Usuario no encontrado
  @ExceptionHandler(UsernameNotFoundException.class)
  public ResponseEntity<ErrorResponse> handleUserNotFound(
      UsernameNotFoundException ex,
      HttpServletRequest request) {

    ErrorResponse error = new ErrorResponse(
        HttpStatus.NOT_FOUND.value(),
        "Not Found",
        "Usuario no encontrado",
        request.getRequestURI());

    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
  }

  // Validaciones
  @ExceptionHandler(ValidationException.class)
  public ResponseEntity<ErrorResponse> handleValidation(
      ValidationException ex,
      HttpServletRequest request) {

    ErrorResponse error = new ErrorResponse(
        HttpStatus.BAD_REQUEST.value(),
        "Bad Request",
        ex.getMessage(),
        request.getRequestURI());

    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
  }

  // Cuenta deshabilitada
  @ExceptionHandler(DisabledException.class)
  public ResponseEntity<ErrorResponse> handleDisabled(
      DisabledException ex,
      HttpServletRequest request) {

    ErrorResponse error = new ErrorResponse(
        HttpStatus.FORBIDDEN.value(),
        "Forbidden",
        "La cuenta está deshabilitada",
        request.getRequestURI());

    return ResponseEntity.status(HttpStatus.FORBIDDEN).body(error);
  }

  // Cuenta bloqueada
  @ExceptionHandler(LockedException.class)
  public ResponseEntity<ErrorResponse> handleLocked(
      LockedException ex,
      HttpServletRequest request) {

    ErrorResponse error = new ErrorResponse(
        HttpStatus.FORBIDDEN.value(),
        "Forbidden",
        "La cuenta está bloqueada",
        request.getRequestURI());

    return ResponseEntity.status(HttpStatus.FORBIDDEN).body(error);
  }

  // Proveedores

  @ExceptionHandler(ProveedorNotFoundException.class)
  public ResponseEntity<ErrorResponse> handleProveedorNotFound(
      ProveedorNotFoundException ex,
      HttpServletRequest request) {

    ErrorResponse error = new ErrorResponse(
        HttpStatus.NOT_FOUND.value(),
        "Not Found",
        ex.getMessage(),
        request.getRequestURI());

    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
  }

  @ExceptionHandler(UnauthorizedAccessException.class)
  public ResponseEntity<ErrorResponse> handleUnauthorizedAccess(
      UnauthorizedAccessException ex,
      HttpServletRequest request) {

    ErrorResponse error = new ErrorResponse(
        HttpStatus.FORBIDDEN.value(),
        "Forbidden",
        ex.getMessage(),
        request.getRequestURI());

    return ResponseEntity.status(HttpStatus.FORBIDDEN).body(error);
  }

  // Error genérico
  @ExceptionHandler(Exception.class)
  public ResponseEntity<ErrorResponse> handleGenericException(
      Exception ex,
      HttpServletRequest request) {

    ErrorResponse error = new ErrorResponse(
        HttpStatus.INTERNAL_SERVER_ERROR.value(),
        "Internal Server Error",
        "Ha ocurrido un error inesperado",
        request.getRequestURI());

    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
  }
}