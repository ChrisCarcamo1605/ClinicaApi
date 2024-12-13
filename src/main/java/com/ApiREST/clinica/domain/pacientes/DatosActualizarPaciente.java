package com.ApiREST.clinica.domain.pacientes;

import jakarta.validation.constraints.Email;

public record DatosActualizarPaciente(Long id ,
                                      String nombre,
                                      String apellido,
                                      String telefono,
                                      @Email
                                      String correo) {
}
