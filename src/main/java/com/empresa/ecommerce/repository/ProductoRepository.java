package com.empresa.ecommerce.repository;

import com.empresa.ecommerce.dto.ProductoTopDto;
import com.empresa.ecommerce.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductoRepository extends JpaRepository<Producto, Long> {
    // Puedes agregar métodos personalizados aquí si los necesitas
    List<Producto> findByActivoTrue();

    @Query(value = "SELECT DISTINCT p.nombre FROM producto p " +
            "INNER JOIN detalle_orden d ON d.producto_id = p.id " +
            "LIMIT 5", nativeQuery = true)
    List<String> findTop5NombresProductos();
}
