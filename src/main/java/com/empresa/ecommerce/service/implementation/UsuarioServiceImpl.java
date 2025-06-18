package com.empresa.ecommerce.service.implementation;

import com.empresa.ecommerce.model.Usuario;
import com.empresa.ecommerce.repository.UsuarioRepository;
import com.empresa.ecommerce.service.interfaces.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public Usuario crearUsuario(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    @Override
    public Usuario actualizarUsuario(Long id, Usuario usuario) {
        Optional<Usuario> existente = usuarioRepository.findById(id);
        if (existente.isPresent()) {
            Usuario actualizado = existente.get();
            actualizado.setNombre(usuario.getNombre());
            actualizado.setEmail(usuario.getEmail());
            actualizado.setPassword(usuario.getPassword());
            actualizado.setRol(usuario.getRol());
            actualizado.setClienteFrecuente(usuario.isClienteFrecuente());
            return usuarioRepository.save(actualizado);
        }
        return null;
    }

    @Override
    public void eliminarUsuario(Long id) {
        usuarioRepository.deleteById(id);
    }

    @Override
    public Optional<Usuario> obtenerUsuarioPorId(Long id) {
        return usuarioRepository.findById(id);
    }

    @Override
    public Usuario login(String email, String password) {
        return (Usuario) usuarioRepository.findByEmailAndPassword(email, password).orElse(null);
    }

    @Override

    public Usuario loginUser(String email, String password) {
        return (Usuario) usuarioRepository.findByEmailAndPassword(email, password).orElse(null);
    }

    @Override
    public Optional<Usuario> obtenerUsuarioPorEmail(String email) {
        return usuarioRepository.findByEmail(email);
    }

    @Override
    public List<Usuario> listarUsuarios() {
        return usuarioRepository.findAll();
    }
}
