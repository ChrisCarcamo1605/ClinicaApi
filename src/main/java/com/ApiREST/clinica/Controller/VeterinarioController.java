package com.ApiREST.clinica.Controller;


import com.ApiREST.clinica.domain.direccion.Direccion;
import com.ApiREST.clinica.domain.veterinario.*;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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

    @PostMapping
    public ResponseEntity<DatosRespuestaVeterinario> RegistrarPaciente(@Valid @RequestBody DatosRegistroVeterinario dtOmedico,
                                                                       UriComponentsBuilder uriBuilder) {

        Veterinario medico = veterinarioRepository.save(new Veterinario().guardarVeterinario(dtOmedico));

        DatosRespuestaVeterinario datosRespuestaMedico = new DatosRespuestaVeterinario(medico.getId(), medico.getNombre(), medico.getCorreo()
                , medico.getTelefono(), medico.getEspecialidad(),
                new Direccion(medico.getDireccion().calle, medico.getDireccion().ciudad
                        , medico.getDireccion().colonia));

        URI url = uriBuilder.path("/medico/{id}").buildAndExpand(medico.getId()).toUri();

        return ResponseEntity.created(url).body(datosRespuestaMedico);
    }

//    @GetMapping
//    public ResponseEntity<Page<DatosListadoVeterinario>> ListarMedico(@PageableDefault(size = 3) Pageable pageable) {
//
//        return ResponseEntity.ok(medicoRepository.findByActivoTrue(pageable).map(DatosListadoVeterinario::new));
//    }

    @GetMapping
    public String ListarMedico(Model model) {

        List<Veterinario> veterinarios = veterinarioRepository.findAll();
        List<DatosRespuestaVeterinario> veterinariosDTO = veterinarios.stream()
                .map(v -> new DatosRespuestaVeterinario(v.getId(), v.getNombre(), v.getCorreo(),
                        v.getTelefono(), v.getEspecialidad(),v.getDireccion()))
                .collect(Collectors.toList());
        model.addAttribute("veterinarios", veterinariosDTO);
        return "crearVeterinario";
    }


    @Transactional
    @PutMapping
    public ResponseEntity<DatosRespuestaVeterinario> ActualizarMedico(@Valid @RequestBody DatosActualizarVeterinario dtOmedico) {
        Veterinario medico = veterinarioRepository.getReferenceById(dtOmedico.id());
        medico.actualizarVeterinario(dtOmedico);
        return ResponseEntity.ok(new DatosRespuestaVeterinario(medico.getId(), medico.getNombre(), medico.getCorreo()
                , medico.getTelefono(), medico.getEspecialidad(),
                new Direccion(medico.getDireccion().calle, medico.getDireccion().ciudad
                        , medico.getDireccion().colonia)));

    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity eliminarMedico(@PathVariable Long id) {
        Veterinario medico = veterinarioRepository.getReferenceById(id);
        medico.eliminarVeterinario();
        return ResponseEntity.noContent().build();
    }
}
