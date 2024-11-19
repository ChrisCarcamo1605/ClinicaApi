package com.ApiREST.clinica.domain.consulta;


import com.ApiREST.clinica.domain.mascota.Mascota;
import com.ApiREST.clinica.domain.usuario.Usuario;
import com.ApiREST.clinica.domain.veterinario.Veterinario;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;


@Table(name = "Consultas")
@Entity(name = "Consulta")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Consulta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonFormat(pattern = "dd/MM/yyyy")
    @JoinColumn(name = "fecha")
    private LocalDate fecha;

    private LocalTime hora;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cliente_id")
    private Usuario usuario;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mascota_id")
    private Mascota mascota;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "veterinario_id")
    private Veterinario veterinario;
    private Boolean activo;




    public Consulta(Usuario usuario, Veterinario veterinario, LocalDate fecha, LocalTime hora,
                    Mascota mascota, boolean activo) {
        this.usuario = usuario;
        this.veterinario = veterinario;
        this.mascota = mascota;
        this.fecha = fecha;
        this.hora = hora;
        this.activo = activo;
    }



}
