package com.ApiREST.clinica.domain.medico;

import jakarta.persistence.Id;

public record DatosActualizarMedico(
                                    @Id
                                    Long id,


                                    String correo,


                                    String telefono,


                                    Especialidad especialidad)
{}

