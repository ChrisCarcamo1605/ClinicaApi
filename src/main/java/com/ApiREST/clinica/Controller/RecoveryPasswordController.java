package com.ApiREST.clinica.Controller;


import com.ApiREST.clinica.domain.usuario.DatosRecuperarPassword;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/RecoveryPassword")
public class RecoveryPasswordController {

    @GetMapping
    public String recoverPassword(Model model) {

        model.addAttribute("datosRecuperarPassword", new DatosRecuperarPassword("", ""));
        return "recoveryPassword";
    }

    @PostMapping("recuperar")
    public String recoverPasswordPost(DatosRecuperarPassword datos, Model model) {
        return "recoveryPassword";
    }
}
