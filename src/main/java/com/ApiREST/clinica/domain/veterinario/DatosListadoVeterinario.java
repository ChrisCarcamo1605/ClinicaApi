package com.ApiREST.clinica.domain.veterinario;

import com.ApiREST.clinica.domain.direccion.Direccion;

public record DatosListadoVeterinario(Long id, String nombre, String correo, String telefono, Especialidad especialidad, Direccion direccion){

    public DatosListadoVeterinario(Veterinario medico) {
        this(medico.getId(),medico.getNombre(),medico.getCorreo(),medico.getTelefono(),medico.getEspecialidad(),
                new Direccion(medico.getDireccion().calle,medico.getDireccion().ciudad,
                        medico.getDireccion().numero));

    }


}
