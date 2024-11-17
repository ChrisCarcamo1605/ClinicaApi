package com.ApiREST.clinica.Controller;


import com.ApiREST.clinica.domain.Autenticacion.DatosAutenticacionUsuario;
import com.ApiREST.clinica.domain.direccion.Direccion;
import com.ApiREST.clinica.domain.usuario.*;
import com.ApiREST.clinica.infra.security.TokenService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/auth")
public class AutenticacionController {


    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    TokenService tokenService;
    @Autowired
    UsuarioRepository usuariosRepository;


    @GetMapping()
    public String mostrarFormularioLogin(Model model) {
        Direccion direccion = new Direccion("","","");
        model.addAttribute("datosAgregarUsuario", new DatosAgregarUsuario("", "",
                "", "", "",direccion));

        model.addAttribute("datosSesionUsuario",new DatosAutenticacionUsuario("",""));
        return "loginRegister";
    }


    @PostMapping("/login")
    public String iniciarSesion(@ModelAttribute @Valid DatosAutenticacionUsuario datosAutenticacionUsuario,
                                HttpServletResponse response,
                                Model model) {
        try {
            Authentication authtoken = new UsernamePasswordAuthenticationToken(
                    datosAutenticacionUsuario.login(), datosAutenticacionUsuario.password());
            var usuarioAutenticado = authenticationManager.authenticate(authtoken);
            var JWTtoken = tokenService.crearToken((Usuario) usuarioAutenticado.getPrincipal());

            Cookie jwtCookie = new Cookie("JWTtoken", JWTtoken);
            jwtCookie.setHttpOnly(true);
            jwtCookie.setSecure(true);
            jwtCookie.setPath("/");
            jwtCookie.setMaxAge(60 * 60 * 24);


            response.addCookie(jwtCookie);

            return "redirect:/home";
        } catch (AuthenticationException e) {
            // Si la autenticación falla
            model.addAttribute("error", "Credenciales incorrectas");
            return "loginRegister";
        }
    }

    @GetMapping("/sign")
    public String mostrarFormularioRegistro(Model model) {
        if (!model.containsAttribute("datosAgregarUsuario")) {
            Direccion direccion = new Direccion("", "", "");
            DatosAgregarUsuario datos = new DatosAgregarUsuario("", "", "", "", "", null);
            model.addAttribute("datosAgregarUsuario", datos);
        }
        return "loginRegister";
    }

    @PostMapping("/sign")
    public String crearUsuario(
            @ModelAttribute @Valid DatosAgregarUsuario datosAgregarUsuario,
            BindingResult bindingResult,
            Model model) {

        // Verificar si el correo electrónico ya existe
        if (usuariosRepository.existsByCorreo(datosAgregarUsuario.correo())) {
            bindingResult.rejectValue("correo", "error.correo.exists", "El correo electrónico ya está registrado");
        }

        // Verificar si el DUI ya existe
        if (usuariosRepository.existsByDui(datosAgregarUsuario.dui())) {
            bindingResult.rejectValue("dui", "error.dui.exists", "El DUI ya está registrado");
        }

        if (bindingResult.hasErrors()) {
            model.addAttribute("errorMessage", "Por favor, corrija los errores en el formulario");
            return "loginRegister";
        }

        try {
            Usuario usuario = new Usuario().guardarUsuario(datosAgregarUsuario);
            usuariosRepository.save(usuario);
            return "redirect:/auth";
        } catch (Exception e) {
            model.addAttribute("errorMessage", "Error al crear el usuario: " + e.getMessage());
            return "formWithErrors";
        }
    }



}
