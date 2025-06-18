package com.empresa.ecommerce.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class DetalleOrdenDto {
    private int cantidad;
    private BigDecimal precioUnitario;
    private Long productoId;
    private Long ordenId;
}
