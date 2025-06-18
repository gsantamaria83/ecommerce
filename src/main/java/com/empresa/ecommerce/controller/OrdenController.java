package com.empresa.ecommerce.controller;

import com.empresa.ecommerce.dto.OrdenDto;
import com.empresa.ecommerce.model.Orden;
import com.empresa.ecommerce.service.interfaces.OrdenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/ordenes")
public class OrdenController {

    @Autowired
    private OrdenService ordenService;

    @PostMapping
    public ResponseEntity<Orden> crear(@RequestBody OrdenDto orden) {
        return ResponseEntity.ok(ordenService.crearOrden(orden));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Orden> actualizar(@PathVariable Long id, @RequestBody Orden orden) {
        Orden actualizada = ordenService.actualizarOrden(id, orden);
        return actualizada != null ? ResponseEntity.ok(actualizada) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        ordenService.eliminarOrden(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Orden> obtenerPorId(@PathVariable Long id) {
        Optional<Orden> orden = ordenService.obtenerOrdenPorId(id);
        return orden.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<Orden>> listar() {
        return ResponseEntity.ok(ordenService.listarOrdenes());
    }

    @GetMapping("/cliente/{clienteId}")
    public ResponseEntity<List<Orden>> listarPorCliente(@PathVariable Long clienteId) {
        return ResponseEntity.ok(ordenService.listarOrdenesPorCliente(clienteId));
    }
}
