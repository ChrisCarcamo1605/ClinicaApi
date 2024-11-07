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


    //Crea tokens con el username y password con una fecha/hora de caducacion
    public String crearToken(Usuario usuario) {


        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.create()
                    .withIssuer("mi_clinica")
                    .withSubject(usuario.getCorreo())
                    .withClaim("ID:", usuario.getId())
                    .withExpiresAt(generarFechaExpiracion())
                    .sign(algorithm);

        } catch (JWTCreationException exception) {
            throw new RuntimeException(exception);
        }


    }


    //verifica el token asegurandose q la firma, caducidad y formato es correcto
    public String verificarToken(String token) {
        if(token == null) {
            throw new RuntimeException("Token nulo");
        }
        DecodedJWT verifier = null;
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            verifier = JWT.require(algorithm)
                    .withIssuer("mi_clinica")
                    .build()
                    .verify(token);

                    verifier.getSubject();
        } catch (JWTVerificationException exception) {
            System.out.println(exception.toString());
        }
        if (verifier.getSubject() == null) {
            throw new RuntimeException("Verifier invalido");
        }
        return verifier.getSubject();
    }

    private Instant generarFechaExpiracion() {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-05:00"));
    }

}

