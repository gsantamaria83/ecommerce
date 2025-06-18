package com.empresa.ecommerce.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
public class OrdenDto {
    private LocalDateTime fecha;
    private BigDecimal total;
    private BigDecimal descuentoAplicado;
    private Long clienteId;
    private Integer cantidad;
    private BigDecimal precioUnitario;
    private Long productoId;
}
