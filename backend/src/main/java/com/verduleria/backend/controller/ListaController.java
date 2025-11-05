package com.verduleria.backend.controller;

import com.verduleria.backend.entity.Lista;
import com.verduleria.backend.entity.ProductoLista;
import com.verduleria.backend.service.ListaService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/listas")
public class ListaController {

  private final ListaService listaService;

  public ListaController(ListaService listaService) {
    this.listaService = listaService;
  }

  @GetMapping
  public List<Lista> getAllListas() {
    return listaService.getAllListas();
  }

  @GetMapping("/{id}")
  public Lista getListaById(@PathVariable Long id) {
    return listaService.getListaById(id);
  }

  @PostMapping
  public Lista createLista(@RequestBody Lista lista) {
    return listaService.createLista(lista);
  }

  @PutMapping("/{id}")
  public Lista updateLista(@PathVariable Long id, @RequestBody Lista lista) {
    return listaService.updateLista(id, lista);
  }

  @DeleteMapping("/{id}")
  public void deleteLista(@PathVariable Long id) {
    listaService.deleteLista(id);
  }

  @PostMapping("/{id}/productos")
  public Lista addProducto(@PathVariable Long id, @RequestBody ProductoLista producto) {
    return listaService.addProducto(id, producto);
  }

  @DeleteMapping("/{id}/productos")
  public Lista removeProducto(@PathVariable Long id, @RequestBody ProductoLista producto) {
    return listaService.quitarProducto(id, producto);
  }

  @GetMapping("/usuario/{usuarioId}")
  public List<Lista> getListasByUsuario(@PathVariable Long usuarioId) {
    return listaService.getListasByUsuario(usuarioId);
  }

  @GetMapping("/proveedor/{proveedorId}")
  public List<Lista> getListasByProveedor(@PathVariable Long proveedorId, Long usuarioId) {
    return listaService.getListasByProveedor(proveedorId, usuarioId);
  }

  @GetMapping("/fecha/{fecha}")
  public List<Lista> getListasByFecha(@PathVariable String fecha, Long usuarioId) {
    LocalDate parsed = LocalDate.parse(fecha);
    return listaService.getListasByFecha(parsed, usuarioId);
  }
}
