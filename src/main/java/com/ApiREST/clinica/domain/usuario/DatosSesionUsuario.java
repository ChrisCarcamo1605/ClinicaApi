package com.ApiREST.clinica.domain.usuario;



public record DatosSesionUsuario(Long id, String login,String password) {
    DatosSesionUsuario(Usuario usuario){
        this(usuario.getId(), usuario.getUsername(), usuario.getPassword());
    }


}
