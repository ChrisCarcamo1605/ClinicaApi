package com.ApiREST.clinica.domain.consulta;

import com.ApiREST.clinica.domain.veterinario.Especialidad;
import com.ApiREST.clinica.domain.veterinario.Veterinario;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;


public record DatosReservaConsulta(
        Long idUsuario,
        Especialidad especialidad,
        Long idMascota,
        Long veterinario,
        @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
        LocalDateTime fechaHora
) {


}
