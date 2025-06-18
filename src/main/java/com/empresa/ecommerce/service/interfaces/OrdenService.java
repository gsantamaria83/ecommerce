package com.empresa.ecommerce.service.interfaces;

import com.empresa.ecommerce.dto.OrdenDto;
import com.empresa.ecommerce.model.Orden;

import java.util.List;
import java.util.Optional;

public interface OrdenService {
    Orden crearOrden(OrdenDto orden);
    Orden actualizarOrden(Long id, Orden orden);
    void eliminarOrden(Long id);
    Optional<Orden> obtenerOrdenPorId(Long id);
    List<Orden> listarOrdenes();
    List<Orden> listarOrdenesPorCliente(Long clienteId);
}
