package com.ApiREST.clinica.domain.veterinario;

import jakarta.persistence.Id;

public record DatosActualizarVeterinario(
                                    @Id
                                    Long id,


                                    String correo,


                                    String telefono,


                                    Especialidad especialidad)
{}

