package com.empresa.ecommerce.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
public class DetalleOrden {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int cantidad;
    private BigDecimal precioUnitario;

    @ManyToOne
    @JoinColumn(name = "producto_id")
    @JsonManagedReference
    private Producto producto;

    @OneToOne
    @JoinColumn(name = "orden_id")
    @JsonBackReference
    private Orden orden;
}
