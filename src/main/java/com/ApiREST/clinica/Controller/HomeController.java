package com.ApiREST.clinica.Controller;

import com.ApiREST.clinica.domain.consulta.DatosDetallesConsulta;
import com.ApiREST.clinica.domain.consulta.DatosReservaConsulta;
import com.ApiREST.clinica.domain.consulta.ReservaConsultaService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/home")
public class HomeController {

    @GetMapping
        public String home(Model model) {

       ;
        model.addAttribute("datos", new DatosReservaConsulta(1l,1l,
                null,null, null));
        return "home";
    }


    @Autowired
    ReservaConsultaService consultaService;

    @PostMapping("/agendarCita")
    public String guardarConsulta(@ModelAttribute @Valid DatosReservaConsulta dtoConsulta) {
        var consulta = consultaService.guardarConsulta(dtoConsulta);
        return "redirect:/home";
    }
}
