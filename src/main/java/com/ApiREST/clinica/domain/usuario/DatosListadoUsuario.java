package com.ApiREST.clinica.domain.usuario;

import com.ApiREST.clinica.domain.direccion.Direccion;

public record DatosListadoUsuario(Long id, String nombre, String correo, String telefono,
                                  String documento, Direccion direccion) {

    public DatosListadoUsuario(Usuario usuario) {
        this(usuario.getId(), usuario.getNombre(), usuario.getCorreo(), usuario.getTelefono(), usuario.getDui(),
                new Direccion(usuario.getDireccion().calle,
                        usuario.getDireccion().ciudad, usuario.getDireccion().colonia));
    }


}
