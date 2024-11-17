package com.ApiREST.clinica.domain.usuario;

import com.ApiREST.clinica.domain.direccion.Direccion;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DatosAgregarUsuario(
        @NotBlank(message = "El nombre es obligatorio")
        String nombre,

        @NotBlank(message = "El correo es obligatorio")
        @Email(message = "Debe ser un correo válido")
        String correo,

        @NotBlank(message = "La contraseña es obligatoria")
        String password,

        @NotBlank(message = "El teléfono es obligatorio")
        String telefono,

        @NotBlank(message = "El DUI es obligatorio")
        String dui,

        @NotNull(message = "La dirección es obligatoria")
        Direccion direccion
) {
    // Constructor que asegura valores no nulos
    public DatosAgregarUsuario {
        nombre = (nombre != null) ? nombre : "";
        correo = (correo != null) ? correo : "";
        password = (password != null) ? password : "";
        telefono = (telefono != null) ? telefono : "";
        dui = (dui != null) ? dui : "";
        direccion = (direccion != null) ? direccion : new Direccion("", "", "");
    }
}



