package com.ApiREST.clinica.domain.consultas;

import com.ApiREST.clinica.domain.medico.Especialidad;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record DatosCrearConsulta(
        @NotBlank
        Long paciente,
        Long medico,
        Especialidad especialidad,
        @NotNull
        @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
        LocalDateTime fecha) {
}

