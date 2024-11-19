package com.ApiREST.clinica.Controller;

import com.ApiREST.clinica.domain.Autenticacion.DatosAutenticacionUsuario;
import com.ApiREST.clinica.domain.direccion.Direccion;
import com.ApiREST.clinica.domain.usuario.DatosAgregarUsuario;
import com.ApiREST.clinica.domain.veterinario.*;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/veterinario")
public class VeterinarioController {

    @Autowired
    private VeterinarioRepository veterinarioRepository;

    @GetMapping("/crear")
    public String mostrarFormularioDeCreacion( Model model) {
        if (!model.containsAttribute("veterinario")) {
            Direccion direccion = new Direccion("", "", "");
            DatosRegistroVeterinario datos = new DatosRegistroVeterinario("","",
                    null,"",direccion);
            model.addAttribute("datosAgregarUsuario", datos);
        }
        return "crearVeterinario";
    }

    @PostMapping("/crear/registrar")
    public String registrarVeterinario(@Valid @ModelAttribute DatosRegistroVeterinario veterinario) {

        veterinarioRepository.save(new Veterinario().guardarVeterinario(veterinario));
        return "redirect:/veterinario";
    }

    @GetMapping
    public String listarVeterinarios(Model model) {
        List<Veterinario> veterinarios = veterinarioRepository.findAll();
        model.addAttribute("veterinarios", veterinarios);
        return "listarVeterinario";
    }

    // Mostrar formulario de edición
    @GetMapping("/editar/{id}")
    public String mostrarFormularioDeEdicion(@PathVariable Long id, Model model) {
        Veterinario veterinario = veterinarioRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Veterinario no encontrado"));
        model.addAttribute("veterinario", veterinario);
        return "editarVeterinario";
    }

    // Actualizar Veterinario
    @PostMapping("/editarVeterinario")
    public String actualizarVeterinario(@Valid Veterinario veterinario) {
        veterinarioRepository.save(veterinario);
        return "redirect:/veterinario";
    }

    // Confirmar la eliminación de un Veterinario
    @GetMapping("/eliminar/{id}")
    public String confirmarEliminarVeterinario(@PathVariable Long id, Model model) {
        Veterinario veterinario = veterinarioRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Veterinario no encontrado"));
        model.addAttribute("veterinario", veterinario);
        return "confirmDelVeterinario";
    }

    // Eliminar Veterinario
    @PostMapping("/eliminar/{id}")
    @Transactional
    public String eliminarVeterinario(@PathVariable Long id) {
        Veterinario veterinario = veterinarioRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Veterinario no encontrado"));
        veterinarioRepository.delete(veterinario);
        return "redirect:/veterinario";
    }
}
