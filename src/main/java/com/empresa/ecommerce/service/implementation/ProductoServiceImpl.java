package com.empresa.ecommerce.service.implementation;

import com.empresa.ecommerce.dto.ProductoDto;
import com.empresa.ecommerce.dto.ProductoTopDto;
import com.empresa.ecommerce.model.Producto;
import com.empresa.ecommerce.repository.ProductoRepository;
import com.empresa.ecommerce.service.interfaces.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductoServiceImpl implements ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    @Override
    public List<Producto> obtenerTodos() {
        return productoRepository.findAll();
    }

    @Override
    public Producto obtenerPorId(Long id) {
        return productoRepository.findById(id).orElse(null);
    }

    @Override
    public Producto guardar(Producto producto) {
        return productoRepository.save(producto);
    }

    @Override
    public Producto actualizar(Long id, Producto producto) {
        Producto existente = productoRepository.findById(id).orElse(null);
        if (existente != null) {
            existente.setNombre(producto.getNombre());
            existente.setDescripcion(producto.getDescripcion());
            existente.setPrecio(producto.getPrecio());
            existente.setActivo(producto.isActivo());
            return productoRepository.save(existente);
        }
        return null;
    }

    @Override
    public void eliminar(Long id) {
        productoRepository.deleteById(id);
    }

    @Override
    public List<Producto> obtenerProductosActivos() {
        return productoRepository.findByActivoTrue();
    }

    @Override
    public List<String> obtenerTop5Productos() {
        return productoRepository.findTop5NombresProductos();
    }
}
