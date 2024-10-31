package com.ApiREST.clinica.domain.Autenticacion;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public record DatosAutenticacionUsuario(String login, String password){

}
