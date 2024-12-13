package com.ApiREST.clinica.domain.consultas;


import com.ApiREST.clinica.domain.medico.Especialidad;
import com.ApiREST.clinica.domain.medico.Medico;
import com.ApiREST.clinica.domain.medico.MedicoRepository;
import com.ApiREST.clinica.domain.pacientes.Paciente;
import com.ApiREST.clinica.domain.pacientes.PacientesRepository;
import com.ApiREST.clinica.infra.errores.ConsultasException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ConsultaService {

    @Autowired
    ConsultaRepository consultaRepository;
    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private PacientesRepository pacientesRepository;


    public Consulta crearConsulta(DatosCrearConsulta datosCrearConsulta) {


        if (datosCrearConsulta.paciente() == null) {
            throw new ConsultasException("El id no hace referencia a ningun usuario");
        }


        Consulta consulta = new Consulta();
        consulta.paciente = pacientesRepository.getReferenceById(datosCrearConsulta.paciente());
        consulta.medico = elegirMedico(datosCrearConsulta.medico(), datosCrearConsulta.especialidad(), datosCrearConsulta.fecha());
        consulta.especialidad = datosCrearConsulta.especialidad();
        consulta.fecha = datosCrearConsulta.fecha();


        System.out.println(consulta.medico.getNombre());
        consultaRepository.save(consulta);

        return consulta;
    }


    public Medico elegirMedico(Long medico, Especialidad especialidad, LocalDateTime fecha) {


        if (medico == null) {
            Medico medicoElegido = null;

            if (especialidad != null) {
                medicoElegido = medicoRepository.elegirVeterinarioDisponible(especialidad, fecha);
            } else {
                medicoElegido = medicoRepository.elegirVeterinarioDisponible(fecha);
            }


            if (medicoElegido != null) {
                medicoElegido.setFecha(fecha);
                return medicoElegido;
            } else {
                throw new ConsultasException("No hay ningun medico disponible para esa fecha y hora");
            }
        } else {


            List<Consulta> medicosOcupados = consultaRepository.findAll();
            Medico med = medicoRepository.getReferenceById(medico);
            for (Consulta m : medicosOcupados) {
                System.out.println(m.medico.getNombre());

                if (m.getFecha().equals(fecha) && m.medico.equals(med)) {
                    System.out.println("no se ha elegido el medico");
                    throw new ConsultasException("Medico ocupado para esa fecha y hora");
                }
            }
            return med;

        }


    }
}
