package com.verduleria.backend.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.verduleria.backend.entity.Proveedor;
import com.verduleria.backend.entity.ProductoParticular;
import com.verduleria.backend.exception.ProductoNotFoundException;
import com.verduleria.backend.exception.ProveedorNotFoundException;
import com.verduleria.backend.exception.UnauthorizedAccessException;
import com.verduleria.backend.repository.ProductoParticularRepository;
import com.verduleria.backend.repository.ProveedorRepository;

@Service
public class ProveedorService {
    
    private final ProveedorRepository proveedorRepository;
    private final ProductoParticularRepository productoRepository;

    public ProveedorService(ProveedorRepository proveedorRepository, ProductoParticularRepository productoRepository) {
        this.proveedorRepository = proveedorRepository;
        this.productoRepository = productoRepository;
    }

    // Obtener todos los proveedores de un usuario
    public List<Proveedor> getProveedoresByUsuario(Long usuarioId) {
        return proveedorRepository.findByUsuarioId(usuarioId);
    }

    // Obtener un proveedor por ID verificando que pertenezca al usuario
    public Proveedor getProveedorByIdAndUsuario(Long id, Long usuarioId) {
        Proveedor proveedor = proveedorRepository.findById(id)
            .orElseThrow(() -> new ProveedorNotFoundException("Proveedor no encontrado"));
        
        if (!proveedor.getUsuario().getId().equals(usuarioId)) {
            throw new UnauthorizedAccessException("No tienes permiso para acceder a este proveedor");
        }
        
        return proveedor;
    }

    // Crear proveedor con productos
    public Proveedor createProveedorConProductos(Proveedor proveedor, List<Long> productosIds, Long usuarioId) {
        // Guardar el proveedor primero
        Proveedor nuevoProveedor = proveedorRepository.save(proveedor);
        
        // Si hay productos, asignarlos
        if (productosIds != null && !productosIds.isEmpty()) {
            asignarProductosAProveedor(nuevoProveedor, productosIds, usuarioId);
        }
        
        return proveedorRepository.findById(nuevoProveedor.getId()).orElse(nuevoProveedor);
    }

    // Asignar productos a un proveedor
    private void asignarProductosAProveedor(Proveedor proveedor, List<Long> productosIds, Long usuarioId) {
        for (Long productoId : productosIds) {
            ProductoParticular producto = productoRepository.findById(productoId)
                .orElseThrow(() -> new ProductoNotFoundException("Producto con ID " + productoId + " no encontrado"));
            
            // Verificar que el producto pertenezca al usuario
            if (!producto.getUsuario().getId().equals(usuarioId)) {
                throw new UnauthorizedAccessException("No tienes permiso para asignar el producto con ID " + productoId);
            }
            
            // Agregar el proveedor al producto (relación bidireccional)
            producto.addProveedor(proveedor);
            productoRepository.save(producto);
        }
    }

    // Actualizar proveedor verificando permisos
    public Proveedor updateProveedor(Long id, Proveedor proveedorActualizado, Long usuarioId) {
        Proveedor proveedor = getProveedorByIdAndUsuario(id, usuarioId);
        
        proveedor.setNombre(proveedorActualizado.getNombre());
        proveedor.setEmail(proveedorActualizado.getEmail());
        proveedor.setTelefono(proveedorActualizado.getTelefono());
        
        return proveedorRepository.save(proveedor);
    }

    // Actualizar proveedor con productos
    public Proveedor updateProveedorConProductos(Long id, Proveedor proveedorActualizado, List<Long> productosIds, Long usuarioId) {
        Proveedor proveedor = getProveedorByIdAndUsuario(id, usuarioId);
        
        // Actualizar datos básicos
        proveedor.setNombre(proveedorActualizado.getNombre());
        proveedor.setEmail(proveedorActualizado.getEmail());
        proveedor.setTelefono(proveedorActualizado.getTelefono());
        proveedorRepository.save(proveedor);
        
        // Primero, quitar este proveedor de todos sus productos actuales
        List<ProductoParticular> productosActuales = proveedor.getProductos();
        for (ProductoParticular producto : productosActuales) {
            producto.removeProveedor(proveedor);
            productoRepository.save(producto);
        }
        
        // Luego, asignar los nuevos productos
        if (productosIds != null && !productosIds.isEmpty()) {
            asignarProductosAProveedor(proveedor, productosIds, usuarioId);
        }
        
        return proveedorRepository.findById(id).orElse(proveedor);
    }

    // Eliminar proveedor verificando permisos
    public void deleteProveedor(Long id, Long usuarioId) {
        Proveedor proveedor = getProveedorByIdAndUsuario(id, usuarioId);
        
        // Antes de eliminar, quitar la referencia del proveedor en los productos
        List<ProductoParticular> productos = proveedor.getProductos();
        for (ProductoParticular producto : productos) {
            producto.removeProveedor(proveedor);
            productoRepository.save(producto);
        }
        
        proveedorRepository.delete(proveedor);
    }
}