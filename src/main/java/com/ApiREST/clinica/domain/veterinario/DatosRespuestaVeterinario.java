package com.ApiREST.clinica.domain.veterinario;

import com.ApiREST.clinica.domain.direccion.Direccion;

public record DatosRespuestaVeterinario(Long id, String nombre, String correo, String telefono, Especialidad especialidad, Direccion direccion) {


}
