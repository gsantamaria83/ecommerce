package com.empresa.ecommerce.service.implementation;

import com.empresa.ecommerce.dto.InventarioUpdateDto;
import com.empresa.ecommerce.model.Inventario;
import com.empresa.ecommerce.model.Producto;
import com.empresa.ecommerce.repository.InventarioRepository;
import com.empresa.ecommerce.repository.ProductoRepository;
import com.empresa.ecommerce.service.interfaces.InventarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InventarioServiceImpl implements InventarioService {

    @Autowired
    private InventarioRepository inventarioRepository;
    @Autowired
    private ProductoRepository productoRepository;

    @Override
    public Inventario crearInventario(Inventario inventario) {
        return inventarioRepository.save(inventario);
    }

    @Override
    public Inventario actualizarInventario(Long id, InventarioUpdateDto inventario) {
        Optional<Inventario> existente = inventarioRepository.findByProductoId(id);
        Optional<Producto> productoActual = productoRepository.findById(id);
        Producto producto = productoActual.orElseThrow(() -> new RuntimeException("Producto no encontrado"));

        if (existente.isPresent()) {
            Inventario actualizado = existente.get();
            actualizado.setCantidad(inventario.getCantidad());
            actualizado.setProducto(producto);
            actualizado.setId(existente.get().getId());
            return inventarioRepository.save(actualizado);
        }
        return null;
    }

    @Override
    public void eliminarInventario(Long id) {
        inventarioRepository.deleteById(id);
    }

    @Override
    public Optional<Inventario> obtenerInventarioPorId(Long id) {
        return inventarioRepository.findById(id);
    }

    @Override
    public Optional<Inventario> obtenerInventarioPorProductoId(Long productoId) {
        return inventarioRepository.findByProductoId(productoId);
    }

    @Override
    public List<Inventario> listarInventarios() {
        return inventarioRepository.findAll();
    }
}
