package com.ApiREST.clinica.domain.pacientes;

import com.ApiREST.clinica.domain.direccion.DTODireccion;
import com.ApiREST.clinica.domain.direccion.Direccion;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DatosRegistrarPaciente(
        @NotBlank
        String nombre,
        @NotBlank
        String apellido,
        @NotBlank
        @Email
        String correo,
        @NotBlank
        String telefono,
        @NotBlank
        String dui,
        @NotNull
        DTODireccion direccion
        ) {
}
