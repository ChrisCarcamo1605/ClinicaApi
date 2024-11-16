package com.ApiREST.clinica.Controller;


import com.ApiREST.clinica.domain.consulta.*;
import com.ApiREST.clinica.domain.usuario.UsuarioRepository;
import com.ApiREST.clinica.domain.veterinario.Veterinario;
import com.ApiREST.clinica.domain.veterinario.VeterinarioRepository;
import com.ApiREST.clinica.infra.errores.ValidacionException;
import jakarta.persistence.EntityManager;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/consultas")
public class ConsultasController {



    @Autowired
    ReservaConsultaService consultaService;

    @PostMapping
    public ResponseEntity guardarConsulta(@RequestBody @Valid DatosReservaConsulta dtoConsulta) {
        var consulta = consultaService.guardarConsulta(dtoConsulta);
        return ResponseEntity.ok(new DatosDetallesConsulta(consulta));
    }


    @DeleteMapping
    public void cancelarConsulta(@RequestBody @PathVariable DatosCancelarConsulta dto) {

    }


}
