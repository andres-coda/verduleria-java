package com.verduleria.backend.exception;

public class ProveedorNotFoundException extends RuntimeException {
  public ProveedorNotFoundException(String message) {
    super(message);
  }
}