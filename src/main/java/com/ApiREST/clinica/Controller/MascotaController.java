package com.ApiREST.clinica.Controller;


import com.ApiREST.clinica.domain.consulta.DatosReservaConsulta;
import com.ApiREST.clinica.domain.mascota.DatosRegistroMascota;
import com.ApiREST.clinica.domain.mascota.Mascota;
import com.ApiREST.clinica.domain.mascota.MascotaRepository;
import com.ApiREST.clinica.domain.mascota.TipoAnimal;
import com.ApiREST.clinica.domain.usuario.Usuario;
import com.ApiREST.clinica.domain.usuario.UsuarioRepository;
import com.ApiREST.clinica.infra.security.TokenService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/mascotas")
public class MascotaController {

    @Autowired
    private MascotaRepository mascotaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;


    @Autowired
    TokenService tokenService;

    @GetMapping()
    public String mostrarFormularioRegistro(Model model, HttpServletRequest request) {
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


        Long idUsuario = tokenService.obtenerIdUsuario(token);


        model.addAttribute("idUsuarioToken", idUsuario);
        model.addAttribute("usuarios", usuarioRepository.findAll());
        model.addAttribute("mascota", new DatosRegistroMascota(idUsuario,"",null));
        return "crearMascota";
    }


    // Manejo del registro de la mascota
    @PostMapping("/registrar")
    public String guardarMascota(DatosRegistroMascota mascotaDatos) {
        Usuario dueño = usuarioRepository.getReferenceById(mascotaDatos.IdDueño());

        Mascota mascotas = new Mascota(dueño,mascotaDatos.nombre(),mascotaDatos.tipoAnimal());

        mascotaRepository.save(mascotas);
        return "redirect:/home";
    }
}