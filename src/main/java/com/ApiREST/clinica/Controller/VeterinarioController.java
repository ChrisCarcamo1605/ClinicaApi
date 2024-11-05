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
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;


@RestController
@RequestMapping("/veterinario")
public class VeterinarioController {

    @Autowired
    VeterinarioRepository medicoRepository;
    
  @PostMapping
  public ResponseEntity<DatosRespuestaVeterinario> RegistrarPaciente(@Valid @RequestBody DatosRegistroVeterinario dtOmedico,
                                                                     UriComponentsBuilder uriBuilder){

      Veterinario medico = medicoRepository.save(new Veterinario().guardarVeterinario(dtOmedico));

      DatosRespuestaVeterinario datosRespuestaMedico = new DatosRespuestaVeterinario(medico.getId(),medico.getNombre(),medico.getCorreo()
              ,medico.getTelefono(), medico.getEspecialidad(),
              new Direccion(medico.getDireccion().calle,medico.getDireccion().ciudad
                      ,medico.getDireccion().numero));

      URI url = uriBuilder.path("/medico/{id}").buildAndExpand(medico.getId()).toUri();

     return ResponseEntity.created(url).body(datosRespuestaMedico);
  }

  @GetMapping
        public ResponseEntity<Page<DatosListadoVeterinario>> ListarMedico(@PageableDefault(size = 3) Pageable pageable){

      return ResponseEntity.ok(medicoRepository.findByActivoTrue(pageable).map(DatosListadoVeterinario::new));
  }

  @Transactional
  @PutMapping
    public ResponseEntity<DatosRespuestaVeterinario> ActualizarMedico(@Valid @RequestBody DatosActualizarVeterinario dtOmedico){
        Veterinario medico = medicoRepository.getReferenceById(dtOmedico.id());
        medico.actualizarVeterinario(dtOmedico);
        return ResponseEntity.ok(new DatosRespuestaVeterinario(medico.getId(),medico.getNombre(),medico.getCorreo()
                ,medico.getTelefono(), medico.getEspecialidad(),
                new Direccion(medico.getDireccion().calle,medico.getDireccion().ciudad
                        ,medico.getDireccion().numero)));

  }
  @DeleteMapping("/{id}")
  @Transactional
    public ResponseEntity eliminarMedico(@PathVariable Long id){
      Veterinario medico = medicoRepository.getReferenceById(id);
      medico.eliminarVeterinario();
      return ResponseEntity.noContent().build();
  }
}
