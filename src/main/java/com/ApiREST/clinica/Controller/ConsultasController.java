package com.ApiREST.clinica.Controller;


import com.ApiREST.clinica.domain.consultas.Consulta;
import com.ApiREST.clinica.domain.consultas.ConsultaService;
import com.ApiREST.clinica.domain.consultas.DatosCrearConsulta;
import com.ApiREST.clinica.domain.consultas.DatosRespuestaConsulta;
import com.ApiREST.clinica.domain.pacientes.DatosRespuestaPaciente;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/consultas")
public class ConsultasController {

    @Autowired
    private ConsultaService consultaService;

    @PostMapping
    public ResponseEntity<DatosRespuestaConsulta> crearConsulta(@RequestBody  DatosCrearConsulta dtoConsulta,
                                                                UriComponentsBuilder uriBuild) {

        System.out.println("Este es el controller: " + dtoConsulta.paciente());
        Consulta consulta = consultaService.crearConsulta(dtoConsulta);
        DatosRespuestaConsulta respuestaPaciente = new DatosRespuestaConsulta(consulta.getPaciente().getNombre(),
                consulta.getMedico().getNombre(), consulta.getFecha(),consulta.getMedico().getEspecialidad());
        URI uri = uriBuild.path("/consultas").buildAndExpand(consulta.getId()).toUri();
        return ResponseEntity.created(uri).body(respuestaPaciente);

    }
}
