package com.ApiREST.clinica.Controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Controller
@RequestMapping("/mascotas")
public class MascotaController {

    @GetMapping
    public String getMascotas() {
        return "crearMascota";
    }

}
