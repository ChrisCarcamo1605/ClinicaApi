package com.ApiREST.clinica.domain.usuario;

import com.ApiREST.clinica.domain.direccion.Direccion;
import jakarta.persistence.Id;

public record DatosActualizarUsuario(
        @Id
        Long id,
        String nombre,
        String correo,
        String telefono,
        String dui,
        Direccion direccion) {


}

