package com.ApiREST.clinica.domain.veterinario;

import com.ApiREST.clinica.domain.direccion.Direccion;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


public record DatosRegistroVeterinario(Long id,
                                       @NotBlank

                                  String nombre,

                                       @NotBlank
                                  String correo,

                                       @NotNull
                                  Especialidad especialidad,

                                       @NotBlank
                                  String telefono,

                                       @NotNull
                                  @Valid
                                  Direccion direccion
                       ){

}
