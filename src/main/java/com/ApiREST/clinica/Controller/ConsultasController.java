package com.ApiREST.clinica.Controller;


import com.ApiREST.clinica.domain.consultas.*;
import com.ApiREST.clinica.domain.pacientes.DatosRespuestaPaciente;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/consultas")
public class ConsultasController {

    @Autowired
    private ConsultaService consultaService;
    @Autowired
    private ConsultaRepository consultaRepository;

    @PostMapping
    @Transactional
    public ResponseEntity<DatosRespuestaConsulta> crearConsulta(@RequestBody  DatosCrearConsulta dtoConsulta,
                                                                UriComponentsBuilder uriBuild) {

        System.out.println("Este es el controller: " + dtoConsulta.paciente());
        Consulta consulta = consultaService.crearConsulta(dtoConsulta);
        DatosRespuestaConsulta respuestaPaciente = new DatosRespuestaConsulta(consulta.getPaciente().getNombre(),
                consulta.getMedico().getNombre(), consulta.getFecha(),consulta.getMedico().getEspecialidad());
        URI uri = uriBuild.path("/consultas").buildAndExpand(consulta.getId()).toUri();
        return ResponseEntity.created(uri).body(respuestaPaciente);

    }

    @GetMapping
    @Transactional
    public ResponseEntity<Page<DatosListadoConsulta>> listarConsultas(@PageableDefault(size = 2) Pageable pageable) {

        return ResponseEntity.ok(consultaRepository.findByActivoTrue(pageable)
                .map(DatosListadoConsulta::new));

    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity eliminarConsulta(@PathVariable Long id) {
        consultaService.eliminarConsulta(id);
        return ResponseEntity.noContent().build();
    }
}
