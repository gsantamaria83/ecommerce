package com.empresa.ecommerce.service.interfaces;

import com.empresa.ecommerce.dto.ProductoTopDto;
import com.empresa.ecommerce.model.Producto;
import java.util.List;

public interface ProductoService {
    List<Producto> obtenerTodos();
    Producto obtenerPorId(Long id);
    Producto guardar(Producto producto);
    Producto actualizar(Long id, Producto producto);
    void eliminar(Long id);
    List<Producto> obtenerProductosActivos();
    List<String> obtenerTop5Productos();
}
