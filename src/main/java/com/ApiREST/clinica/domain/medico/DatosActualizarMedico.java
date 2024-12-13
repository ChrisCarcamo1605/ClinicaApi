package com.ApiREST.clinica.domain.medico;

import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;

public record DatosActualizarMedico(
                                    @Id
                                    Long id,

                                    @Email
                                    String correo,


                                    String telefono,


                                    Especialidad especialidad)
{}

