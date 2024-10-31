package com.ApiREST.clinica.domain.medico;

import com.ApiREST.clinica.domain.direccion.Direccion;

public record DatosRespuestaMedico(Long id, String nombre, String correo, String telefono, Especialidad especialidad, Direccion direccion) {


}
