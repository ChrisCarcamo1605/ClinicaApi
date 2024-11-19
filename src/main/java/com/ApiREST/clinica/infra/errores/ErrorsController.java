package com.ApiREST.clinica.infra.errores;


import jakarta.persistence.EntityNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import org.springframework.web.bind.annotation.RestControllerAdvice;


@ControllerAdvice
public class ErrorsController {

   @ExceptionHandler(EntityNotFoundException.class)
   public ResponseEntity tratarError404() {return ResponseEntity.notFound().build();}

   @ExceptionHandler(MethodArgumentNotValidException.class)
   public ResponseEntity tratarError400(MethodArgumentNotValidException e) {

      var error = e.getFieldErrors().stream().map(DatosErrores::new);
      return ResponseEntity.badRequest().body(error);}

   @ExceptionHandler(DataIntegrityViolationException.class)
   public String tratarError500(DataIntegrityViolationException e) {
      return e.getMessage();
   }

   public record DatosErrores(String campo, String error){
      public DatosErrores(FieldError error){ this(error.getField(), error.getDefaultMessage());}
   }



}
