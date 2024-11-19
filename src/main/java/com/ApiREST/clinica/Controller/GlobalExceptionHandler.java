package com.ApiREST.clinica.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public String handleError(Model model, Exception ex) {
        // Para errores generales
        model.addAttribute("errorMessage", ex.getMessage());

        List<String> errors = new ArrayList<>();
        model.addAttribute("errors", errors);

        return "/formWithErrors";
    }
}
