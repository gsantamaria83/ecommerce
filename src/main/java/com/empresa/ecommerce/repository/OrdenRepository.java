package com.empresa.ecommerce.repository;

import com.empresa.ecommerce.model.Orden;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OrdenRepository extends JpaRepository<Orden, Long> {
    List<Orden> findByClienteId(Long clienteId);
    Optional<Orden> findTopByOrderByFechaDesc();
}
