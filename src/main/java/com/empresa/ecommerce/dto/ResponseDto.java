package com.empresa.ecommerce.dto;

import com.empresa.ecommerce.model.Usuario;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ResponseDto {
    String status;
    String mensaje;
    Usuario usuario;

   /* public ResponseDto(String status, String mensaje){
        this.status = status;
        this.mensaje = mensaje;
    }

    public String getStatus() {
        return status;
    }

    public String getMensaje() {
        return mensaje;
    }*/
}
