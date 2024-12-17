package com.ApiREST.clinica.domain.consultas;

import com.ApiREST.clinica.domain.medico.Especialidad;

import java.time.LocalDateTime;

public record DatosListadoConsulta(Long id, String paciente, String medico, LocalDateTime fecha,
                                   Especialidad especialidad,Boolean activo) {


    public DatosListadoConsulta(Consulta consulta){
        this(consulta.getId(), consulta.getPaciente().getNombre(), consulta.getMedico().getNombre()
                , consulta.getFecha(), consulta.getEspecialidad(),consulta.getActivo());
    }

}
