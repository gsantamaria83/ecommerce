package com.empresa.ecommerce.service.implementation;

import com.empresa.ecommerce.dto.OrdenDto;
import com.empresa.ecommerce.model.DetalleOrden;
import com.empresa.ecommerce.model.Orden;
import com.empresa.ecommerce.model.Producto;
import com.empresa.ecommerce.model.Usuario;
import com.empresa.ecommerce.repository.DetalleOrdenRepository;
import com.empresa.ecommerce.repository.OrdenRepository;
import com.empresa.ecommerce.repository.ProductoRepository;
import com.empresa.ecommerce.repository.UsuarioRepository;
import com.empresa.ecommerce.service.interfaces.OrdenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrdenServiceImpl implements OrdenService {

    @Autowired
    private OrdenRepository ordenRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private DetalleOrdenRepository detalleOrdenRepository;
    @Autowired
    private ProductoRepository productoRepository;

    @Override
    public Orden crearOrden(OrdenDto orden) {
        DetalleOrden detalleOrden = new DetalleOrden();

        Orden ordenNormal = new Orden();
        Usuario usuario = usuarioRepository.findById(orden.getClienteId())
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));
        Producto producto = productoRepository.findById(orden.getProductoId())
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));
        ordenNormal.setCliente(usuario);
        ordenNormal.setTotal(orden.getTotal());
        ordenNormal.setFecha(orden.getFecha());
        ordenNormal.setDescuentoAplicado(orden.getDescuentoAplicado());
        Orden ordenEJec =  ordenRepository.save(ordenNormal);
        Optional<Orden> ordenOpcional = ordenRepository.findTopByOrderByFechaDesc();
        detalleOrden.setCantidad(orden.getCantidad());
        detalleOrden.setProducto(producto);
        detalleOrden.setPrecioUnitario(orden.getPrecioUnitario());
        detalleOrden.setOrden(ordenOpcional.get());
        detalleOrdenRepository.save(detalleOrden);


        return ordenEJec;
    }

    @Override
    public Orden actualizarOrden(Long id, Orden orden) {
        Optional<Orden> existente = ordenRepository.findById(id);
        if (existente.isPresent()) {
            Orden actual = existente.get();
            actual.setFecha(orden.getFecha());
            actual.setTotal(orden.getTotal());
            actual.setDescuentoAplicado(orden.getDescuentoAplicado());
            actual.setCliente(orden.getCliente());
            actual.setDetalles(orden.getDetalles());
            return ordenRepository.save(actual);
        }
        return null;
    }

    @Override
    public void eliminarOrden(Long id) {
        ordenRepository.deleteById(id);
    }

    @Override
    public Optional<Orden> obtenerOrdenPorId(Long id) {

        return ordenRepository.findById(id);

    }

    @Override
    public List<Orden> listarOrdenes() {
        return ordenRepository.findAll();
    }

    @Override
    public List<Orden> listarOrdenesPorCliente(Long clienteId) {
        return ordenRepository.findByClienteId(clienteId);
    }
}
