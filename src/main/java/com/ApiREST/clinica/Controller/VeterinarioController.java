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
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;


@RestController
@RequestMapping("/veterinario")
public class VeterinarioController {

    @Autowired
    VeterinarioRepository medicoRepository;
    @Autowired
    private VeterinarioRepository veterinarioRepository;

    @PostMapping
  public ResponseEntity<DatosRespuestaVeterinario> RegistrarPaciente(@Valid @RequestBody DatosRegistroVeterinario dtOmedico,
                                                                     UriComponentsBuilder uriBuilder){

      Veterinario medico = medicoRepository.save(new Veterinario().guardarVeterinario(dtOmedico));

      DatosRespuestaVeterinario datosRespuestaMedico = new DatosRespuestaVeterinario(medico.getId(),medico.getNombre(),medico.getCorreo()
              ,medico.getTelefono(), medico.getEspecialidad(),
              new Direccion(medico.getDireccion().calle,medico.getDireccion().ciudad
                      ,medico.getDireccion().colonia));

      URI url = uriBuilder.path("/medico/{id}").buildAndExpand(medico.getId()).toUri();

     return ResponseEntity.created(url).body(datosRespuestaMedico);
  }

  @GetMapping
        public String ListarMedico(Model model){
      List<Veterinario> veterinarios = veterinarioRepository.findAll();
      model.addAttribute("veterinarios", veterinarios); // Agrega la lista de veterinarios al modelo

      return "veterinarios";
  }

  @Transactional
  @PutMapping
    public ResponseEntity<DatosRespuestaVeterinario> ActualizarMedico(@Valid @RequestBody DatosActualizarVeterinario dtOmedico){
        Veterinario medico = medicoRepository.getReferenceById(dtOmedico.id());
        medico.actualizarVeterinario(dtOmedico);
        return ResponseEntity.ok(new DatosRespuestaVeterinario(medico.getId(),medico.getNombre(),medico.getCorreo()
                ,medico.getTelefono(), medico.getEspecialidad(),
                new Direccion(medico.getDireccion().calle,medico.getDireccion().ciudad
                        ,medico.getDireccion().colonia)));

  }
  @DeleteMapping("/{id}")
  @Transactional
    public ResponseEntity eliminarMedico(@PathVariable Long id){
      Veterinario medico = medicoRepository.getReferenceById(id);
      medico.eliminarVeterinario();
      return ResponseEntity.noContent().build();
  }
}
