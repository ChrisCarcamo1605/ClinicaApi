package com.ApiREST.clinica.Controller;


import com.ApiREST.clinica.domain.direccion.Direccion;
import com.ApiREST.clinica.domain.medico.*;
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
@RequestMapping("/medico")
public class MedicoController {

    @Autowired
    MedicoRepository medicoRepository;

    
  @PostMapping
  public ResponseEntity<DatosRespuestaMedico> RegistrarPaciente(@Valid @RequestBody DatosRegistromedico dtOmedico,
                                                                UriComponentsBuilder uriBuilder){

      Medico medico = medicoRepository.save(new Medico().guardarMedico(dtOmedico));

      DatosRespuestaMedico datosRespuestaMedico = new DatosRespuestaMedico(medico.getId(),medico.getNombre(),medico.getCorreo()
              ,medico.getTelefono(), medico.getEspecialidad(),
              new Direccion(medico.getDireccion().calle,medico.getDireccion().ciudad
                      ,medico.getDireccion().numero));

      URI url = uriBuilder.path("/medico/{id}").buildAndExpand(medico.getId()).toUri();

     return ResponseEntity.created(url).body(datosRespuestaMedico);
  }

  @GetMapping
        public ResponseEntity<Page<DatosListadoMedico>> ListarMedico(@PageableDefault(size = 3) Pageable pageable){

      return ResponseEntity.ok(medicoRepository.findByActivoTrue(pageable).map(DatosListadoMedico::new));
  }

  @Transactional
  @PutMapping
    public ResponseEntity<DatosRespuestaMedico> ActualizarMedico(@Valid @RequestBody DatosActualizarMedico dtOmedico){
        Medico medico = medicoRepository.getReferenceById(dtOmedico.id());
        medico.actualizarMedico(dtOmedico);
        return ResponseEntity.ok(new DatosRespuestaMedico(medico.getId(),medico.getNombre(),medico.getCorreo()
                ,medico.getTelefono(), medico.getEspecialidad(),
                new Direccion(medico.getDireccion().calle,medico.getDireccion().ciudad
                        ,medico.getDireccion().numero)));

  }
  @DeleteMapping("/{id}")
  @Transactional
    public ResponseEntity eliminarMedico(@PathVariable Long id){
      Medico medico = medicoRepository.getReferenceById(id);
      medico.eliminarMedico();
      return ResponseEntity.noContent().build();
  }
}
