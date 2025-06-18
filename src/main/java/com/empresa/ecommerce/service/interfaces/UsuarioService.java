package com.empresa.ecommerce.service.interfaces;

import com.empresa.ecommerce.model.Usuario;

import java.util.List;
import java.util.Optional;

public interface UsuarioService {
    Usuario crearUsuario(Usuario usuario);
    Usuario actualizarUsuario(Long id, Usuario usuario);
    void eliminarUsuario(Long id);
    Usuario login(String email, String password);
    Usuario loginUser(String email, String password);
    Optional<Usuario> obtenerUsuarioPorId(Long id);
    Optional<Usuario> obtenerUsuarioPorEmail(String email);
    List<Usuario> listarUsuarios();
}
