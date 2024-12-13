package com.ApiREST.clinica.domain.pacientes;

import com.ApiREST.clinica.domain.direccion.DTODireccion;
import com.ApiREST.clinica.domain.direccion.Direccion;

public record DatosRespuestaPaciente(String nombre, String apellido, String correo, String telefono, String dui,
                                     Direccion direccion
) {


}
