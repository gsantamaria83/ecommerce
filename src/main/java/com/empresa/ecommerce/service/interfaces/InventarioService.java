package com.empresa.ecommerce.service.interfaces;

import com.empresa.ecommerce.dto.InventarioUpdateDto;
import com.empresa.ecommerce.model.Inventario;

import java.util.List;
import java.util.Optional;

public interface InventarioService {
    Inventario crearInventario(Inventario inventario);
    Inventario actualizarInventario(Long id, InventarioUpdateDto inventario);
    void eliminarInventario(Long id);
    Optional<Inventario> obtenerInventarioPorId(Long id);
    Optional<Inventario> obtenerInventarioPorProductoId(Long productoId);
    List<Inventario> listarInventarios();
}
