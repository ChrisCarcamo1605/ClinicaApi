package com.ApiREST.clinica.Controller;

import ch.qos.logback.core.model.Model;
import com.ApiREST.clinica.domain.usuario.DatosAgregarUsuario;
import com.ApiREST.clinica.domain.usuario.Usuario;
import com.ApiREST.clinica.domain.usuario.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/crearusuario")
public class CrearUsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping
    public String cargarIniciarSesion(Model model) {

        return "loginRegister";
    }


    @PostMapping
    public String crearUsuario(@ModelAttribute DatosAgregarUsuario datosAgregarUsuario) {


        usuarioRepository.save(new Usuario().guardarUsuario(datosAgregarUsuario));
        return "redirect:/login";
    }

}
