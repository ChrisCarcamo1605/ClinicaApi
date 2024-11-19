package com.ApiREST.clinica.Controller;

import com.ApiREST.clinica.domain.direccion.Direccion;
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

    // Crear un nuevo Veterinario desde el formulario
    @GetMapping("/crear")
    public String mostrarFormularioDeCreacion() {
        return "crearVeterinario"; // Retorna la vista de creación de veterinarios
    }

    @PostMapping
    public String registrarVeterinario(@Valid Veterinario veterinario, Model model) {
        // Guardar el nuevo veterinario en la base de datos
        veterinarioRepository.save(veterinario);
        model.addAttribute("veterinarios", veterinarioRepository.findAll());
        return "redirect:/veterinario"; // Redirige a la lista de veterinarios
    }

    // Listar todos los Veterinarios
    @GetMapping
    public String listarVeterinarios(Model model) {
        List<Veterinario> veterinarios = veterinarioRepository.findAll();
        model.addAttribute("veterinarios", veterinarios);
        return "listarVeterinario"; // Retorna la vista de listado de veterinarios
    }

    // Mostrar formulario de edición
    @GetMapping("/editar/{id}")
    public String mostrarFormularioDeEdicion(@PathVariable Long id, Model model) {
        Veterinario veterinario = veterinarioRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Veterinario no encontrado"));
        model.addAttribute("veterinario", veterinario);
        return "editarVeterinario"; // Retorna la vista de edición
    }

    // Actualizar Veterinario
    @PostMapping("/editarVeterinario")
    public String actualizarVeterinario(@Valid Veterinario veterinario) {
        veterinarioRepository.save(veterinario); // Guardar los cambios
        return "redirect:/veterinario"; // Redirige a la lista de veterinarios
    }

    // Confirmar la eliminación de un Veterinario
    @GetMapping("/eliminar/{id}")
    public String confirmarEliminarVeterinario(@PathVariable Long id, Model model) {
        Veterinario veterinario = veterinarioRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Veterinario no encontrado"));
        model.addAttribute("veterinario", veterinario);
        return "confirmDelVeterinario"; // Vista de confirmación de eliminación
    }

    // Eliminar Veterinario
    @PostMapping("/eliminar/{id}")
    @Transactional
    public String eliminarVeterinario(@PathVariable Long id) {
        Veterinario veterinario = veterinarioRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Veterinario no encontrado"));
        veterinarioRepository.delete(veterinario); // Eliminar del repositorio
        return "redirect:/veterinario"; // Redirigir al listado de veterinarios
    }
}
