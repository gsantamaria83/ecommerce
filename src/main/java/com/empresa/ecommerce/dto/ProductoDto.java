package com.empresa.ecommerce.dto;

import com.empresa.ecommerce.model.Inventario;
import jakarta.persistence.*;

import java.math.BigDecimal;

public class ProductoDto {
    private Long id;
    private String nombre;
    private String descripcion;
    private BigDecimal precio;
    private boolean activo;
}
