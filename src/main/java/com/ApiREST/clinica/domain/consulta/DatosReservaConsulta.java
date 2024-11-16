package com.ApiREST.clinica.domain.consulta;

import com.ApiREST.clinica.domain.mascota.Mascota;
import com.ApiREST.clinica.domain.veterinario.Especialidad;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;


public record DatosReservaConsulta(
        Long idReserva,
        Long idVeterinario,
        Long idUsuario,
        Especialidad especialidad,
        Long idMascota,

        @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
        LocalDateTime fecha) {


}
