package com.ApiREST.clinica.domain.medico;

import com.ApiREST.clinica.domain.direccion.Direccion;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.UniqueElements;


public record DatosRegistromedico(Long id,
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
