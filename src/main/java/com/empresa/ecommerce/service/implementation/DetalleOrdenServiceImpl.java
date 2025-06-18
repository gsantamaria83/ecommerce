package com.empresa.ecommerce.service.implementation;

import com.empresa.ecommerce.dto.DetalleOrdenDto;
import com.empresa.ecommerce.dto.DetalleOrdenQueryDto;
import com.empresa.ecommerce.dto.DetalleOrdenQueryListAllDto;
import com.empresa.ecommerce.model.DetalleOrden;
import com.empresa.ecommerce.model.Orden;
import com.empresa.ecommerce.model.Producto;
import com.empresa.ecommerce.repository.DetalleOrdenRepository;
import com.empresa.ecommerce.repository.OrdenRepository;
import com.empresa.ecommerce.repository.ProductoRepository;
import com.empresa.ecommerce.service.interfaces.DetalleOrdenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.stream.Collectors;

import java.util.List;
import java.util.Optional;

@Service
public class DetalleOrdenServiceImpl implements DetalleOrdenService {

    @Autowired
    private DetalleOrdenRepository detalleOrdenRepository;
    @Autowired
    private ProductoRepository productoRepository;
    @Autowired
    private OrdenRepository ordenRepository;

    //@Override
    /*public DetalleOrden crearDetalleOrden(DetalleOrden detalleOrden) {
        Producto producto = productoRepository.findById(detalleOrden.getProducto().getId())
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));
        Orden orden = ordenRepository.findById(detalleOrden.getOrden().getId())
                .orElseThrow(() -> new RuntimeException("Orden no encontrada"));
        detalleOrden.setProducto(producto);
        detalleOrden.setOrden(orden);
        return detalleOrdenRepository.save(detalleOrden);
    }*/
    @Override
    public DetalleOrden crearDetalleOrden(DetalleOrdenDto dto) {
        DetalleOrdenQueryDto detalleQuery = new DetalleOrdenQueryDto();
        Producto producto = productoRepository.findById(dto.getProductoId())
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

        Orden orden = ordenRepository.findById(dto.getOrdenId())
                .orElseThrow(() -> new RuntimeException("Orden no encontrada"));

        DetalleOrden detalle = new DetalleOrden();
        detalle.setCantidad(dto.getCantidad());
        detalle.setPrecioUnitario(dto.getPrecioUnitario());
        detalle.setProducto(producto);
        detalle.setOrden(orden);

        return detalleOrdenRepository.save(detalle);
    }

    @Override
    public DetalleOrdenQueryListAllDto actualizarDetalleOrden(Long id,DetalleOrdenDto detalleOrden) {
        Optional<DetalleOrden> existente = detalleOrdenRepository.findById(id);
        if (existente.isPresent()) {
            Producto producto = productoRepository.findById(detalleOrden.getProductoId())
                    .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

            Orden orden = ordenRepository.findById(detalleOrden.getOrdenId())
                    .orElseThrow(() -> new RuntimeException("Orden no encontrada"));

            DetalleOrden actualizado = existente.get();
            actualizado.setCantidad(detalleOrden.getCantidad());
            actualizado.setPrecioUnitario(detalleOrden.getPrecioUnitario());
            actualizado.setProducto(producto);
            actualizado.setOrden(orden);
            detalleOrdenRepository.save(actualizado);

            DetalleOrden detalle = detalleOrdenRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Detalle no encontrado"));

            return new DetalleOrdenQueryListAllDto(
                    detalle.getId(),
                    detalle.getCantidad(),
                    detalle.getPrecioUnitario(),
                    detalle.getProducto().getNombre(),
                    detalle.getOrden().getId()
            );

        }
        return null;
    }

    @Override
    public void eliminarDetalleOrden(Long id) {
        detalleOrdenRepository.deleteById(id);
    }

    @Override
    public Optional<DetalleOrdenQueryListAllDto> obtenerDetalleOrdenPorId(Long id) {
        return detalleOrdenRepository.findById(id)
                .map(detalle -> new DetalleOrdenQueryListAllDto(
                        detalle.getId(),
                        detalle.getCantidad(),
                        detalle.getPrecioUnitario(),
                        detalle.getProducto().getNombre(),
                        detalle.getOrden().getId()
                ));
    }

    @Override
    public List<DetalleOrdenQueryListAllDto> listarDetalleOrdenes() {
        return detalleOrdenRepository.findAll().stream()
                .map(detalle -> new DetalleOrdenQueryListAllDto(
                        detalle.getId(),
                        detalle.getCantidad(),
                        detalle.getPrecioUnitario(),
                        detalle.getProducto().getNombre(),
                        detalle.getOrden().getId()
                ))
                .collect(Collectors.toList());

    }

    @Override
    public List<DetalleOrden> obtenerPorOrdenId(Long ordenId) {
        return detalleOrdenRepository.findByOrdenId(ordenId);
    }
}
