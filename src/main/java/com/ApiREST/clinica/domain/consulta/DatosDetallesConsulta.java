package com.ApiREST.clinica.domain.consulta;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public record DatosDetallesConsulta(Long idConsulta, String nombreUsuario, LocalDate fecha, LocalTime hora, String mascota,
                                    String veterinario) {

    public DatosDetallesConsulta(Consulta consulta) {
        this(consulta.getId(),consulta.getUsuario().getNombre(),consulta.getFecha(),consulta.getHora(),consulta.getMascota().getNombre(),
                consulta.getVeterinario().getNombre());

    }

}
