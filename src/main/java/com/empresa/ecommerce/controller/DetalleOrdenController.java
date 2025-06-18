package com.empresa.ecommerce.controller;

import com.empresa.ecommerce.dto.DetalleOrdenDto;
import com.empresa.ecommerce.dto.DetalleOrdenQueryDto;
import com.empresa.ecommerce.dto.DetalleOrdenQueryListAllDto;
import com.empresa.ecommerce.model.DetalleOrden;
import com.empresa.ecommerce.service.interfaces.DetalleOrdenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/detalle-ordenes")
public class DetalleOrdenController {

    @Autowired
    private DetalleOrdenService detalleOrdenService;

    @PostMapping
    public ResponseEntity<DetalleOrden> crear(@RequestBody DetalleOrdenDto detalleOrden) {

        return ResponseEntity.ok(detalleOrdenService.crearDetalleOrden(detalleOrden));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DetalleOrdenQueryListAllDto> actualizar(@PathVariable Long id, @RequestBody DetalleOrdenDto detalleOrden) {
        DetalleOrdenQueryListAllDto actualizado = detalleOrdenService.actualizarDetalleOrden(id, detalleOrden);
        return actualizado != null ? ResponseEntity.ok(actualizado) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        detalleOrdenService.eliminarDetalleOrden(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetalleOrdenQueryListAllDto> obtenerPorId(@PathVariable Long id) {
        Optional<DetalleOrdenQueryListAllDto> detalle = detalleOrdenService.obtenerDetalleOrdenPorId(id);
        return detalle.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<DetalleOrdenQueryListAllDto>> listarTodos() {
        return ResponseEntity.ok(detalleOrdenService.listarDetalleOrdenes());
    }

    @GetMapping("/orden/{ordenId}")
    public ResponseEntity<List<DetalleOrden>> obtenerPorOrden(@PathVariable Long ordenId) {
        return ResponseEntity.ok(detalleOrdenService.obtenerPorOrdenId(ordenId));
    }
}
