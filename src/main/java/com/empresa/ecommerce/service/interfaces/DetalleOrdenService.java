package com.empresa.ecommerce.service.interfaces;

import com.empresa.ecommerce.dto.DetalleOrdenDto;
import com.empresa.ecommerce.dto.DetalleOrdenQueryDto;
import com.empresa.ecommerce.dto.DetalleOrdenQueryListAllDto;
import com.empresa.ecommerce.model.DetalleOrden;
import java.util.List;
import java.util.Optional;

public interface DetalleOrdenService {
    DetalleOrden crearDetalleOrden(DetalleOrdenDto dto);
    //DetalleOrden crearDetalleOrden(DetalleOrden detalleOrden);
    DetalleOrdenQueryListAllDto actualizarDetalleOrden(Long id,DetalleOrdenDto detalleOrden);
    void eliminarDetalleOrden(Long id);
    Optional<DetalleOrdenQueryListAllDto> obtenerDetalleOrdenPorId(Long id);
    List<DetalleOrdenQueryListAllDto> listarDetalleOrdenes();
    List<DetalleOrden> obtenerPorOrdenId(Long ordenId);
}
