package com.ApiREST.clinica.Controller;


import com.ApiREST.clinica.domain.consulta.Consulta;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/consultas")
public class ConsultasController {



    @PostMapping
    public void guardarConsulta(@RequestBody Consulta consulta){


}
}
