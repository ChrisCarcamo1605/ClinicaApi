package com.ApiREST.clinica.Controller;

import com.ApiREST.clinica.domain.consulta.DatosDetallesConsulta;
import com.ApiREST.clinica.domain.consulta.DatosReservaConsulta;
import com.ApiREST.clinica.domain.consulta.ReservaConsultaService;
import com.ApiREST.clinica.domain.mascota.MascotaRepository;
import com.ApiREST.clinica.infra.security.TokenService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/home")
public class HomeController {


    @Autowired
    MascotaRepository mascotaRepository;
    @Autowired
    ReservaConsultaService consultaService;
    @Autowired
    TokenService tokenService;

    @GetMapping
    public String home(Model model, HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        String token = null;


        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("JWTtoken".equals(cookie.getName())) {
                    token = cookie.getValue();
                    break;
                }
            }
        }

        if (token != null) {
            System.out.println("Token encontradoPRRO: " + token);
        } else {
            System.out.println("No se encontró el token en la cookie.");
        }


        Long idUsuario = tokenService.obtenerIdUsuario(token);


        model.addAttribute("idUsuario", idUsuario);

        model.addAttribute("mascotas", mascotaRepository.findAll());
        model.addAttribute("datos", new DatosReservaConsulta( idUsuario,
                null, 1l, null));
        return "home";
    }




    @PostMapping("/agendarCita")
    public String guardarConsulta(@ModelAttribute @Valid DatosReservaConsulta dtoConsulta) {
        var consulta = consultaService.guardarConsulta(dtoConsulta);
        return "redirect:/home";
    }
}
