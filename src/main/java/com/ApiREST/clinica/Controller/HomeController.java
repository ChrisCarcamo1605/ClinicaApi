package com.ApiREST.clinica.Controller;

import com.ApiREST.clinica.domain.consulta.Consulta;
import com.ApiREST.clinica.domain.consulta.DatosReservaConsulta;
import com.ApiREST.clinica.domain.consulta.ReservaConsultaService;
import com.ApiREST.clinica.domain.mascota.MascotaRepository;
import com.ApiREST.clinica.domain.veterinario.Veterinario;
import com.ApiREST.clinica.domain.veterinario.VeterinarioRepository;
import com.ApiREST.clinica.infra.security.TokenService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

import java.time.LocalTime;
import java.util.List;

@Controller
@RequestMapping("/home")
public class HomeController {


    @Autowired
    MascotaRepository mascotaRepository;
    @Autowired
    ReservaConsultaService consultaService;

    @Autowired
    VeterinarioRepository veterinarioRepository;
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

        model.addAttribute("mascotas", mascotaRepository.findByDueño_Id(idUsuario));
        model.addAttribute("datos", new DatosReservaConsulta(idUsuario,
                null, 1l, null, null));
        return "home";
    }


    @PostMapping("/agendarCita")
    public String agendarCita(
            @RequestParam("fecha") @DateTimeFormat(pattern = "dd-MM-yyyy") LocalDate fecha,
            @ModelAttribute @Valid DatosReservaConsulta dto
    ) {
        try {

            consultaService.guardarConsulta(dto, fecha);

            return "redirect:/home";
        } catch (RuntimeException e) {

            return "redirect:/" + e.getMessage();
        }
    }
}
