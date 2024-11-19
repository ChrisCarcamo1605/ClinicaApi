package com.ApiREST.clinica.domain.veterinario;

import com.ApiREST.clinica.domain.direccion.Direccion;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DatosRegistroVeterinario(
                                       @NotBlank(message = "El nombre es obligatorio")
                                       String nombre,

                                       @NotBlank(message = "El correo es obligatorio")
                                       @Email(message = "Debe ser un correo válido")
                                       String correo,

                                       Especialidad especialidad,

                                       @NotBlank(message = "El teléfono es obligatorio")
                                       String telefono,



                                       @NotNull(message = "La dirección es obligatoria")
                                       Direccion direccion
) {
    // Constructor que asegura valores no nulos
    public DatosRegistroVeterinario {
        nombre = (nombre != null) ? nombre : "";
        correo = (correo != null) ? correo : "";
        especialidad = (especialidad != null) ? especialidad : null;
        telefono = (telefono != null) ? telefono : "";
        direccion = (direccion != null) ? direccion : new Direccion("", "", "");
    }}
