package com.ApiREST.clinica.domain.consultas;

import com.ApiREST.clinica.domain.medico.Especialidad;

import java.time.LocalDateTime;

public record DatosRespuestaConsulta(String paciente, String medico, LocalDateTime fecha, Especialidad especialidad) {
}
