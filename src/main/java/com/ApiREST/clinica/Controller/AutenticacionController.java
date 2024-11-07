package com.ApiREST.clinica.Controller;


import com.ApiREST.clinica.domain.direccion.Direccion;
import com.ApiREST.clinica.domain.usuario.*;
import com.ApiREST.clinica.infra.security.DatosJWTtoken;
import com.ApiREST.clinica.infra.security.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/login")
public class AutenticacionController {


    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    TokenService tokenService;
    @Autowired
    UsuarioRepository usuariosRepository;



    @PostMapping
    public ResponseEntity iniciarSesion(@RequestBody @Valid DatosSesionUsuario datosSesionUsuario) {

        Authentication authtoken = new UsernamePasswordAuthenticationToken(datosSesionUsuario.correo(),
                datosSesionUsuario.password());
        var usuarioAutenticado = authenticationManager.authenticate(authtoken);
        var JWTtoken = tokenService.crearToken((Usuario) usuarioAutenticado.getPrincipal());
        System.out.println(new DatosJWTtoken(JWTtoken));
        return ResponseEntity.ok().body(new DatosJWTtoken(JWTtoken));
    }


}
