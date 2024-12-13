package com.ApiREST.clinica.Controller;

import com.ApiREST.clinica.domain.direccion.Direccion;
import com.ApiREST.clinica.domain.pacientes.*;
import jakarta.persistence.EntityManager;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;


@RestController
@RequestMapping("/pacientes")
public class PacienteController {

    @Autowired
    PacientesRepository pacientesRepository;

    @PostMapping
    public ResponseEntity<DatosRespuestaPaciente> CrearCita(@Valid @RequestBody DatosRegistrarPaciente dtoPaciente,
                                                            UriComponentsBuilder uriBuilder) {

        Paciente paciente = pacientesRepository.save(new Paciente().registrarPaciente(dtoPaciente));

        DatosRespuestaPaciente datosRespuestaPaciente = new DatosRespuestaPaciente(paciente.getNombre(), paciente.getApellido()
                , paciente.getCorreo(), paciente.getTelefono(), paciente.getDui(), new Direccion(paciente.getDireccion().calle,
                paciente.getDireccion().ciudad, paciente.getDireccion().numero));

        URI url = uriBuilder.path("/pacientes").buildAndExpand(paciente.getId()).toUri();
        return ResponseEntity.created(url).body(datosRespuestaPaciente);

    }

    @GetMapping
    @Transactional
    public ResponseEntity<Page<DatosListadoPaciente>> listarPacientes(@PageableDefault(size = 2) Pageable pageable) {

        return ResponseEntity.ok(pacientesRepository.findByActivoTrue(pageable).map(DatosListadoPaciente::new));
    }

    @PutMapping
    @Transactional
    public ResponseEntity<DatosRespuestaPaciente> actualizarPaciente(@Valid @RequestBody DatosActualizarPaciente
                                                                                 datosActualizarPaciente){
        Paciente paciente = pacientesRepository.getReferenceById(datosActualizarPaciente.id());
        paciente.actualizarPaciente(datosActualizarPaciente);
        pacientesRepository.save(paciente);

        DatosRespuestaPaciente dto = new DatosRespuestaPaciente(paciente.getNombre(),paciente.getApellido(),
                paciente.getCorreo(),paciente.getTelefono(),paciente.getDui(),
                new Direccion(paciente.getDireccion().calle,paciente.getDireccion().ciudad,paciente.getDireccion().numero));
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity eliminarPaciente(@PathVariable Long id) {

        Paciente paciente = pacientesRepository.getReferenceById(id);
        pacientesRepository.save(paciente.eliminarPaciente());
        return ResponseEntity.noContent().build();

    }






}
