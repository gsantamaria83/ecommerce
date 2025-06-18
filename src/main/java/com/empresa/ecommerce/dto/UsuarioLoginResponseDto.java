package com.empresa.ecommerce.dto;

public class UsuarioLoginResponseDto {
    private Long id;
    private String nombre;
    private String email;

    public UsuarioLoginResponseDto(Long id, String nombre, String email) {
        this.id = id;
        this.nombre = nombre;
        this.email = email;
    }

    // Getters y setters
}
