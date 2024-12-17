package com.ApiREST.clinica.Controller;

import com.ApiREST.clinica.domain.usuario.DatosCrearUsuario;
import com.ApiREST.clinica.domain.usuario.DatosLogin;
import com.ApiREST.clinica.domain.usuario.Usuario;
import com.ApiREST.clinica.domain.usuario.UsuarioRepository;
import com.ApiREST.clinica.infra.security.DatosJWTtoken;
import com.ApiREST.clinica.infra.security.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/login")
public class UsuarioController {

    @Autowired
    UsuarioRepository usuarioRepository;
    @Autowired
    AuthenticationManager autenticationManager;
    @Autowired
    TokenService tokenService;

    @PostMapping
    public ResponseEntity<DatosJWTtoken> login(@RequestBody @Valid DatosLogin datosLogin) {
        Authentication authtoken = new UsernamePasswordAuthenticationToken(datosLogin.username(),
                datosLogin.password());

        var usuarioAutenticado = autenticationManager.authenticate(authtoken);

        var tokenAutenticado = tokenService.crearToken((Usuario) usuarioAutenticado.getPrincipal());

        return ResponseEntity.ok().body(new DatosJWTtoken(tokenAutenticado));

    }

    @PostMapping("/crearUsuario")
    public ResponseEntity<DatosLogin> crearUsuario(@RequestBody @Valid DatosCrearUsuario datosLogin,
                                                   UriComponentsBuilder uriBuilder) {

        String passwordHash = BCrypt.hashpw(datosLogin.password(), BCrypt.gensalt());
        Usuario usuario = usuarioRepository.save(new Usuario(datosLogin.username(), passwordHash));

        URI uri = uriBuilder.path("/usuarios").buildAndExpand(usuario.getId()).toUri();
        return ResponseEntity.created(uri).build();

    }
}
