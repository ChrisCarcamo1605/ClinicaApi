package com.ApiREST.clinica.infra.security;


import com.ApiREST.clinica.domain.usuario.DatosSesionUsuario;
import com.ApiREST.clinica.domain.usuario.Usuario;
import com.ApiREST.clinica.domain.usuario.UsuarioRepository;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;


@Service
public class TokenService extends JWT {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Value("${spring.application.secretProperties}")
    private String secret;





    public String crearToken(Usuario usuario){


        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.create()
                    .withIssuer("mi_clinica")
                    .withSubject(usuario.getUsername())
                    .withClaim("ID:", usuario.getId())
                    .withExpiresAt(generarFechaExpiracion())
                    .sign(algorithm);

        } catch (JWTCreationException exception){
              throw  new RuntimeException(exception);
            }


    }

    public Usuario verificarToken(String token){

        DecodedJWT decodedJWT;
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            JWTVerifier verifier = JWT.require(algorithm)
                    .withSubject("Authorization")
                    .withIssuer("mi_clinica")
                    .build();

                decodedJWT =  verifier.verify(token);
             return verificarToken(token);
        } catch (JWTVerificationException exception){
           throw  new RuntimeException(exception);
        }

    }

    private Instant generarFechaExpiracion(){
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-05:00"));
    }

}

