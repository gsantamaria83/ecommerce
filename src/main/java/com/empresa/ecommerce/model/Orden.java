package com.empresa.ecommerce.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@Entity
public class Orden {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime fecha;
    private BigDecimal total;
    private BigDecimal descuentoAplicado;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario cliente;

    @OneToOne(mappedBy = "orden", cascade = CascadeType.ALL)
    @JsonManagedReference
    private DetalleOrden detalles;
}
