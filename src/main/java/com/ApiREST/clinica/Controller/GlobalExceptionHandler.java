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

        // Para errores de validación (si los tienes)
        List<String> errors = new ArrayList<>();
        // Añade tus errores a la lista
        model.addAttribute("errors", errors);

        return "formWithErrors";
    }
}
