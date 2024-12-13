package com.ApiREST.clinica.domain.pacientes;


import com.ApiREST.clinica.domain.direccion.Direccion;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.boot.autoconfigure.web.WebProperties;

@Table(name = "Pacientes")
@Entity(name = "Paciente")
@NoArgsConstructor
@Getter
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Paciente {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    Long id;
    String nombre;
    String apellido;
    String telefono;
    String correo;
    String dui;
    Direccion direccion;
    Boolean activo;



    public Paciente registrarPaciente(DatosRegistrarPaciente dtoPaciente){
        this.nombre = dtoPaciente.nombre();
        this.apellido = dtoPaciente.apellido();
        this.telefono = dtoPaciente.telefono();
        this.correo = dtoPaciente.correo();
        this.dui = dtoPaciente.dui();
        this.direccion =  new Direccion(dtoPaciente.direccion().calle(),dtoPaciente.direccion().ciudad(),
                dtoPaciente.direccion().numero());
        this.activo =true;
        return this;
    }

    public Paciente actualizarPaciente(DatosActualizarPaciente dtoPaciente){

        if(dtoPaciente.nombre()!=null){
        this.nombre = dtoPaciente.nombre();
        }
        if(dtoPaciente.apellido()!=null){
            this.apellido = dtoPaciente.apellido();
        }
        if(dtoPaciente.telefono()!=null){
            this.telefono = dtoPaciente.telefono();
        }
        if(dtoPaciente.correo()!=null){
            this.correo = dtoPaciente.correo();
        }
         return this;
    }

    public Paciente eliminarPaciente(){
        this.activo = false;
        return this;
    }

}
