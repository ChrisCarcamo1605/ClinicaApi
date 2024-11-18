package com.ApiREST.clinica.Controller;


import com.ApiREST.clinica.domain.mascota.Mascota;
import com.ApiREST.clinica.domain.mascota.MascotaRepository;
import com.ApiREST.clinica.domain.usuario.Usuario;
import com.ApiREST.clinica.domain.usuario.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MascotaController {

    @Autowired
    private MascotaRepository mascotaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    // Vista para registrar una nueva mascota
    @GetMapping("/mascotas/registrar")
    public String mostrarFormularioRegistro(Model model) {
        // Obtener todos los usuarios (dueños)
        model.addAttribute("usuarios", usuarioRepository.findAll());
        model.addAttribute("mascota", new Mascota()); // Un objeto vacío para el formulario
        return "crearMascota";  // Retorna la vista del formulario
    }

    // Manejo del registro de la mascota
    @PostMapping("/mascotas/guardar")
    public String guardarMascota(Mascota mascota) {
        // Guardar la mascota en la base de datos
        mascotaRepository.save(mascota);
        return "redirect:/home";  // Redirige a la página principal después de guardar
    }
}