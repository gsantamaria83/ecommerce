package com.empresa.ecommerce.controller;

import com.empresa.ecommerce.dto.InventarioDto;
import com.empresa.ecommerce.dto.InventarioUpdateDto;
import com.empresa.ecommerce.model.Inventario;
import com.empresa.ecommerce.model.Producto;
import com.empresa.ecommerce.repository.ProductoRepository;
import com.empresa.ecommerce.service.interfaces.InventarioService;
import com.empresa.ecommerce.service.interfaces.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/inventarios")
public class InventarioController {

    @Autowired
    private InventarioService inventarioService;
    @Autowired
    private ProductoService productoService;
    @Autowired
    private ProductoRepository productoRepository;

    //@PostMapping
    //public ResponseEntity<Inventario> crear(@RequestBody Inventario inventario) {
      //  return ResponseEntity.ok(inventarioService.crearInventario(inventario));
    //}

    @PostMapping
    public ResponseEntity<Inventario> crear(@RequestBody InventarioDto dto) {

        Producto producto = productoRepository.findById(dto.getProductoId())
                .orElseThrow(() -> new RuntimeException("Producto no encontrado con id " + dto.getProductoId()));

        // Crear el inventario
        Inventario inventario = new Inventario();
        inventario.setCantidad(dto.getCantidad());
        inventario.setProducto(producto);

        Inventario inventarioGuardado = inventarioService.crearInventario(inventario);
        return ResponseEntity.ok(inventarioGuardado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Inventario> actualizar(@PathVariable Long id, @RequestBody InventarioUpdateDto inventario) {
        Inventario actualizado = inventarioService.actualizarInventario(id, inventario);
        return actualizado != null ? ResponseEntity.ok(actualizado) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        inventarioService.eliminarInventario(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Inventario> obtenerPorId(@PathVariable Long id) {
        Optional<Inventario> inventario = inventarioService.obtenerInventarioPorId(id);
        return inventario.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/producto/{productoId}")
    public ResponseEntity<Inventario> obtenerPorProducto(@PathVariable Long productoId) {
        Optional<Inventario> inventario = inventarioService.obtenerInventarioPorProductoId(productoId);
        return inventario.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<Inventario>> listar() {
        return ResponseEntity.ok(inventarioService.listarInventarios());
    }
}
