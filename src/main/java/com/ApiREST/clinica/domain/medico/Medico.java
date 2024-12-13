package com.ApiREST.clinica.domain.medico;


import com.ApiREST.clinica.domain.direccion.Direccion;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Table(name = "medicos")
@Entity(name = "Medico")
@EqualsAndHashCode(of = "id")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Medico {
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
    Boolean activo = true;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    LocalDateTime fecha;

    public Medico guardarMedico(DatosRegistromedico dtomedico) {
        this.activo = activo;
        this.nombre = dtomedico.nombre();
        this.correo = dtomedico.correo();
        this.especialidad = dtomedico.especialidad();
        this.direccion = new Direccion(dtomedico.direccion().calle, dtomedico.direccion().ciudad, dtomedico.direccion().numero);
        this.telefono = dtomedico.telefono();
        return this;
    }

    public Medico actualizarMedico(DatosActualizarMedico dtomedico) {

        if (dtomedico.telefono() != null) {
            this.telefono = dtomedico.telefono();
        }
        if (dtomedico.correo() != null) {
            this.correo = dtomedico.correo();
        }
        if (dtomedico.especialidad() != null) {
            this.especialidad = dtomedico.especialidad();
        }
        return this;
    }

    public void eliminarMedico() {

        this.activo = false;

    }


}
