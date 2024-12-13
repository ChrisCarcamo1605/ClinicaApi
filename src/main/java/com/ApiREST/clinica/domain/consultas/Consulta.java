package com.ApiREST.clinica.domain.consultas;


import com.ApiREST.clinica.domain.medico.Especialidad;
import com.ApiREST.clinica.domain.medico.Medico;
import com.ApiREST.clinica.domain.pacientes.Paciente;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity(name = "Consulta")
@Table(name = "consultas")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Consulta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;


    @JoinColumn(name = "medico")
    @ManyToOne(fetch = FetchType.LAZY)
    Medico medico;

    @Enumerated(EnumType.STRING)
    Especialidad especialidad;


    @JoinColumn(name = "paciente")
    @ManyToOne(fetch = FetchType.LAZY)
    Paciente paciente;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    LocalDateTime fecha;
}
