package com.ApiREST.clinica.domain.veterinario;


import com.ApiREST.clinica.domain.direccion.Direccion;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Table(name = "veterinarios")
@Entity(name = "Veterinario")
@EqualsAndHashCode(of = "id")
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Veterinario {
    @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
     long id;
     String nombre;

     @Enumerated(EnumType.STRING)
     Especialidad especialidad;
     String telefono;
     String correo;
     @Embedded
     Direccion direccion;
     Boolean activo=true;
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
     LocalDateTime fecha;

     public Veterinario guardarVeterinario(DatosRegistroVeterinario dtoVeterinario){
         this.activo=activo;
         this.nombre = dtoVeterinario.nombre();
         this.correo = dtoVeterinario.correo();
         this.especialidad = dtoVeterinario.especialidad();
         this.direccion = new Direccion(dtoVeterinario.direccion().calle,dtoVeterinario.direccion().ciudad,dtoVeterinario.direccion().colonia);
         this.telefono= dtoVeterinario.telefono();
         this.fecha =null;
         return this;
     }

     public Veterinario actualizarVeterinario(DatosActualizarVeterinario dtoVeterinario){

         if(dtoVeterinario.telefono()!=null){
             this.telefono = dtoVeterinario.telefono();
         }
         if(dtoVeterinario.correo()!=null){
             this.correo = dtoVeterinario.correo();
         }
         if(dtoVeterinario.especialidad()!=null){
             this.especialidad = dtoVeterinario.especialidad();
         }
        return this;
     }
     public void eliminarVeterinario(){

         this.activo=false;

     }
}
