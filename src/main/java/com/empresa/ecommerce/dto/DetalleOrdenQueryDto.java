package com.empresa.ecommerce.dto;

import com.empresa.ecommerce.model.Orden;
import com.empresa.ecommerce.model.Producto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class DetalleOrdenQueryDto {
    private Long id;
    private int cantidad;
    private BigDecimal precioUnitario;
    private Producto producto;
    private Orden orden;
}


