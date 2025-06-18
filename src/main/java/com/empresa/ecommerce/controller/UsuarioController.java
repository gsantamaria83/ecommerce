package com.empresa.ecommerce.controller;

import com.empresa.ecommerce.dto.LoginRequestDto;
import com.empresa.ecommerce.dto.ResponseDto;
import com.empresa.ecommerce.model.Usuario;
import com.empresa.ecommerce.service.interfaces.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/registro")
    public ResponseEntity<Usuario> crearUsuario(@RequestBody Usuario usuario) {
        return ResponseEntity.ok(usuarioService.crearUsuario(usuario));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Usuario> actualizarUsuario(@PathVariable Long id, @RequestBody Usuario usuario) {
        Usuario actualizado = usuarioService.actualizarUsuario(id, usuario);
        return actualizado != null ? ResponseEntity.ok(actualizado) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarUsuario(@PathVariable Long id) {
        usuarioService.eliminarUsuario(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> obtenerUsuarioPorId(@PathVariable Long id) {
        Optional<Usuario> usuario = usuarioService.obtenerUsuarioPorId(id);
        return usuario.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<Usuario> obtenerUsuarioPorEmail(@PathVariable String email) {
        Optional<Usuario> usuario = usuarioService.obtenerUsuarioPorEmail(email);
        return usuario.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<Usuario>> listarUsuarios() {
        return ResponseEntity.ok(usuarioService.listarUsuarios());
    }

    @PostMapping("/login")
    public ResponseEntity<ResponseDto> login(@RequestBody LoginRequestDto loginRequest) {
        System.out.println(">> Email recibido: " + loginRequest.getEmail());
        System.out.println(">> Password recibido: " + loginRequest.getPassword());
        ResponseDto responseDto = new ResponseDto("","",null);
        Usuario usuarioOptional = usuarioService.loginUser(
                loginRequest.getEmail(),
                loginRequest.getPassword()
        );



        if (usuarioOptional != null) {

            usuarioOptional.setToken("asdasdadas");
            responseDto = new ResponseDto(
                    "OK",
                    "Usuario validado",
                    usuarioOptional
            );


        } else {
            responseDto = new ResponseDto(
                    "Unauthorized",
                    "Credenciales incorrectas",
                    null
            );

        }
        return ResponseEntity.ok(responseDto);
    }

}
