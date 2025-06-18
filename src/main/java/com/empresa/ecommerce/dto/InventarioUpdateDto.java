package com.empresa.ecommerce.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InventarioUpdateDto {
    Long productoId;
    Integer cantidad;
}
