package com.ApiREST.clinica.Controller;

import com.ApiREST.clinica.domain.usuario.DatosAgregarUsuario;
import com.ApiREST.clinica.domain.usuario.Usuario;
import com.ApiREST.clinica.domain.usuario.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/crearusuario")
public class CrearUsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @PostMapping
    ResponseEntity crearUsuario(@RequestBody DatosAgregarUsuario datosAgregarUsuario) {
        Usuario usuario = usuarioRepository.save(new Usuario().guardarUsuario(datosAgregarUsuario));
        return ResponseEntity.ok().body(usuario);
    }
}
