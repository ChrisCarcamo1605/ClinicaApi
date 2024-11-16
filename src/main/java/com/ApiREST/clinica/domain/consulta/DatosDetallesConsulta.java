package com.ApiREST.clinica.domain.consulta;

import com.ApiREST.clinica.domain.mascota.Mascota;
import com.ApiREST.clinica.domain.usuario.Usuario;
import com.ApiREST.clinica.domain.veterinario.Veterinario;

import java.time.LocalDateTime;

public record DatosDetallesConsulta(Long idConsulta, String nombreUsuario, LocalDateTime fecha, String mascota,
                                    String veterinario) {

    public DatosDetallesConsulta(Consulta consulta) {
        this(consulta.getId(),consulta.getIdUsuario().getNombre(),consulta.getFecha(),consulta.getIdMascota().getNombre(),
                consulta.getIdVeterinario().getNombre());

    }

}
