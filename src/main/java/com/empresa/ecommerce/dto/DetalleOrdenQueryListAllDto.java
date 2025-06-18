package com.empresa.ecommerce.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class DetalleOrdenQueryListAllDto {
    private Long id;
    private int cantidad;
    private BigDecimal precioUnitario;
    private String nombreProducto; // en lugar de objeto Producto
    private Long idOrden; // en lugar de objeto Orden

    public DetalleOrdenQueryListAllDto() {
    }

    public DetalleOrdenQueryListAllDto(Long id, int cantidad, BigDecimal precioUnitario, String nombreProducto, Long idOrden) {
        this.id = id;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
        this.nombreProducto = nombreProducto;
        this.idOrden = idOrden;
    }
}




