package com.empresa.ecommerce.controller;

import com.empresa.ecommerce.dto.ProductoTopDto;
import com.empresa.ecommerce.model.Producto;
import com.empresa.ecommerce.dto.ProductoDto;
import com.empresa.ecommerce.service.interfaces.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    @GetMapping
    public List<Producto> getAll() {
        return productoService.obtenerTodos();
    }

    @GetMapping("/{id}")
    public Producto getById(@PathVariable Long id) {
        return productoService.obtenerPorId(id);
    }

    @PostMapping
    public Producto create(@RequestBody Producto producto) {
        return productoService.guardar(producto);
    }

    @PutMapping("/{id}")
    public Producto update(@PathVariable Long id, @RequestBody Producto producto) {
        return productoService.actualizar(id, producto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        productoService.eliminar(id);
    }

    @GetMapping("/activos")
    public List<Producto> obtenerProductosActivos() {
        return productoService.obtenerProductosActivos();
    }

    @GetMapping("/topcinco")
    public List<String> obtenerTopCincoProductos() {
        return productoService.obtenerTop5Productos();
    }
}

