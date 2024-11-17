package com.ApiREST.clinica.domain.direccion;


import jakarta.persistence.Embeddable;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Embeddable
@Getter
@Setter
public class Direccion {

    public String calle;
    public String ciudad;
    public String colonia;


    public Direccion(@Valid DTODireccion dtoDireccion) {
        this.calle = dtoDireccion.calle();
        this.ciudad = dtoDireccion.ciudad();
        this.colonia = dtoDireccion.colonia();

    }

    public Direccion() {
        this.calle = "";
        this.ciudad = "";
        this.colonia = "";
    }

    public Direccion(String calle, String ciudad, String colonia) {
        this.calle = calle;
        this.ciudad = ciudad;
        this.colonia = colonia;

    }


}
