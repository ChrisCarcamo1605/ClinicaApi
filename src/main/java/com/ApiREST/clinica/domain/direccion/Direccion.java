package com.ApiREST.clinica.domain.direccion;


import jakarta.persistence.Embeddable;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class Direccion {
    public String calle;
    public String ciudad;
    public String colonia;

    public Direccion(@Valid DTODireccion dtoDireccion){
        this.calle = dtoDireccion.calle();
        this.ciudad = dtoDireccion.ciudad();
        this.colonia = dtoDireccion.colonia();

    }


}
