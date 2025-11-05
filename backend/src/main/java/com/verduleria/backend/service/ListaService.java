package com.verduleria.backend.service;

import com.verduleria.backend.entity.Lista;
import com.verduleria.backend.entity.ProductoLista;
import com.verduleria.backend.repository.ListaRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ListaService {

  private final ListaRepository listaRepository;

  public ListaService(ListaRepository listaRepository) {
    this.listaRepository = listaRepository;
  }

  // Obtener todas las listas
  public List<Lista> getAllListas() {
    return listaRepository.findAll();
  }

  // Obtener lista por ID
  public Lista getListaById(Long id) {
    return listaRepository.findById(id).orElse(null);
  }

  // Crear nueva lista
  public Lista createLista(Lista lista) {
    return listaRepository.save(lista);
  }

  // Actualizar lista existente
  public Lista updateLista(Long id, Lista listaActualizada) {
    Optional<Lista> listaOpt = listaRepository.findById(id);
    if (listaOpt.isPresent()) {
      Lista lista = listaOpt.get();
      lista.setFecha(listaActualizada.getFecha());
      lista.setProveedor(listaActualizada.getProveedor());
      listaRepository.save(lista);
      return lista;
    }
    return null;
  }

  // Eliminar lista
  public void deleteLista(Long id) {
    listaRepository.deleteById(id);
  }

  // Agregar producto a la lista
  public Lista addProducto(Long idLista, ProductoLista producto) {
    Lista lista = listaRepository.findById(idLista).orElse(null);
    if (lista != null) {
      lista.addProducto(producto);
      listaRepository.save(lista);
    }
    return lista;
  }

  // Quitar producto de la lista
  public Lista quitarProducto(Long idLista, ProductoLista producto) {
    Lista lista = listaRepository.findById(idLista).orElse(null);
    if (lista != null) {
      lista.quitarProducto(producto);
      listaRepository.save(lista);
    }
    return lista;
  }

  // Buscar listas por usuario
  public List<Lista> getListasByUsuario(Long usuarioId) {
    return listaRepository.findByUsuarioId(usuarioId);
  }

  // Buscar listas por proveedor
  public List<Lista> getListasByProveedor(Long proveedorId, Long usuarioId) {
    return listaRepository.findByUsuarioIdAndProveedorId(proveedorId, usuarioId);
  }

  // Buscar listas por fecha
  public List<Lista> getListasByFecha(LocalDate fecha, Long usuarioId) {
    return listaRepository.findByUsuarioIdAndFecha(usuarioId, fecha);
  }
}
