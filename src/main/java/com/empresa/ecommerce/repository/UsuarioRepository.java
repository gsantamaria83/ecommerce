package com.empresa.ecommerce.repository;

import com.empresa.ecommerce.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByEmail(String email);
    boolean existsByEmail(String email);

    Optional<Object> findByEmailAndPassword(String email, String password);
}
