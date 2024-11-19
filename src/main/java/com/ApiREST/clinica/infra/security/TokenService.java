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
public class TokenService {

    private final UsuarioRepository usuarioRepository;
    private final String secret;

    private static final String CLAIM_ID_KEY = "id";
    private static final String CLAIM_ISSUER = "mi_clinica";
    private static final int TOKEN_EXPIRATION_HOURS = 2;

    public TokenService(UsuarioRepository usuarioRepository, @Value("${spring.application.secretProperties}") String secret) {
        this.usuarioRepository = usuarioRepository;
        this.secret = secret;
    }

    public String crearToken(Usuario usuario) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.create()
                    .withIssuer(CLAIM_ISSUER)
                    .withSubject(usuario.getCorreo())
                    .withClaim(CLAIM_ID_KEY, usuario.getId())
                    .withExpiresAt(getExpirationDate())
                    .sign(algorithm);
        } catch (JWTCreationException e) {
            throw new RuntimeException("Error al crear el token", e);
        }
    }

    public String verificarToken(String token) {
        if (token == null) {
            throw new RuntimeException("Token nulo");
        }

        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            DecodedJWT decodedJWT = JWT.require(algorithm)
                    .withIssuer(CLAIM_ISSUER)
                    .build()
                    .verify(token);
            System.out.println("Token válido: " + decodedJWT.getToken());
            System.out.println("Asunto (subject): " + decodedJWT.getSubject());
            System.out.println("Emisor (issuer): " + decodedJWT.getIssuer());
            System.out.println("ID del usuario: " + decodedJWT.getClaim(CLAIM_ID_KEY).asLong());
            return decodedJWT.getSubject();
        } catch (JWTVerificationException e) {
            System.out.println("Error al verificar el token: " + e.getMessage());
            throw new RuntimeException("Token inválido", e);
        }
    }

    public Long obtenerClaim(String token, String claimKey) {
        if (token == null || claimKey == null || claimKey.isEmpty()) {
            throw new IllegalArgumentException("Necesitas Iniciar Sesión");
        }

        try {
            DecodedJWT decodedJWT = JWT.decode(token);
            Long claimValue = decodedJWT.getClaim(claimKey).asLong();

            System.out.println("Valor del claim '" + claimKey + "': " + claimValue);

            if (claimValue == null) {
                System.out.println("Advertencia: El claim '" + claimKey + "' no existe o es nulo en el token.");
            }

            return claimValue;
        } catch (Exception e) {
            System.out.println("Error al extraer el claim '" + claimKey + "' del token.");
            throw new RuntimeException("Error al extraer el claim del token", e);
        }
    }

    public Long obtenerIdUsuario(String token) {
        Long claim = obtenerClaim(token, CLAIM_ID_KEY);
        if (claim == null) {
            return null;
        }

        return claim;
    }

    private Instant getExpirationDate() {
        return LocalDateTime.now().plusHours(TOKEN_EXPIRATION_HOURS).toInstant(ZoneOffset.of("-05:00"));
    }
}
