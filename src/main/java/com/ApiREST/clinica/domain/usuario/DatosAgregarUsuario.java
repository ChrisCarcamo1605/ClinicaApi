package com.ApiREST.clinica.domain.usuario;

import com.ApiREST.clinica.domain.direccion.Direccion;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DatosAgregarUsuario(
        @NotBlank
        String nombre,
        @NotBlank
        @Email
        String correo,
        @NotBlank
        String password,

        @NotBlank
        String telefono,
        @NotBlank
        String dui,
        @NotNull
        Direccion direccion
) {
}
