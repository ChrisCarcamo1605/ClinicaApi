package com.ApiREST.clinica.infra.errores;


import jakarta.persistence.EntityNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;

import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class ErrorsController {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity tratarError404() {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity tratarError400(MethodArgumentNotValidException e) {

        var error = e.getFieldErrors().stream().map(DatosErrores::new);
        return ResponseEntity.badRequest().body(error);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity tratarError500(DataIntegrityViolationException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }

    @ExceptionHandler(ConsultasException.class)
    public ResponseEntity ConsultaValidation(ConsultasException e) {

        var errorResponse = new DatosErrores(
                HttpStatus.BAD_REQUEST.value(),
                "ERROR AL CREAR CONSULTA",
                e.getMessage());

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(errorResponse);
    }

    public record DatosErrores(
            Integer codigo,
            String campo,
            String error
    ) {

        public DatosErrores(FieldError error) {
            this(null, error.getField(), error.getDefaultMessage());
        }


    }


}
