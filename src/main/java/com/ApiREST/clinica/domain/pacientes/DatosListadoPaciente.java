package com.ApiREST.clinica.domain.pacientes;

import com.ApiREST.clinica.domain.direccion.Direccion;
import com.ApiREST.clinica.domain.medico.DatosListadoMedico;

public record DatosListadoPaciente(Long id, String nombre, String apellido, String telefono, Direccion direccion) {

    public DatosListadoPaciente(Paciente paciente) {


        this(paciente.getId(),
                paciente.getNombre(),
                paciente.getApellido(),
                paciente.getTelefono(),
                new Direccion(paciente.getDireccion().calle, paciente.getDireccion().ciudad,
                        paciente.getDireccion().numero)

        );
    }
}
