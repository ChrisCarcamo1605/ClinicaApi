package com.ApiREST.clinica.domain.usuario;

import com.ApiREST.clinica.domain.direccion.Direccion;

public record DatosRespuestaUsuario(

        String nombre,

        String correo,

        String telefono,

        String documento,

        Direccion direccion) {
}
