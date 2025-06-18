package com.empresa.ecommerce.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HolaController {

    @GetMapping("/")
    public String hola() {
        return "¡Bienvenido a la tienda online!";
    }
}
