package com.ApiREST.clinica.domain.consulta;


import com.ApiREST.clinica.domain.mascota.Mascota;
import com.ApiREST.clinica.domain.usuario.Usuario;
import com.ApiREST.clinica.domain.veterinario.Veterinario;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Table(name = "Consultas")
@Entity(name = "Consulta")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Consulta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    @JoinColumn(name = "fecha")
    private LocalDateTime fecha;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idusuario")
    private Usuario idUsuario;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idmascota")
    private Mascota idMascota;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idveterinario")
    private Veterinario idVeterinario;
    private Boolean activo;
    private MotivoCancelamiento motivoCancelamiento;

    public Consulta(Usuario usuario, Veterinario veterinario, LocalDateTime fecha,
                    Mascota mascota, boolean activo) {
        this.idUsuario = usuario;
        this.idVeterinario = veterinario;
        this.idMascota = mascota;
        this.fecha = fecha;
        this.activo = activo;
    }


    public void cancelarConsulta(MotivoCancelamiento dto) {

        motivoCancelamiento = dto;
        this.activo = false;

    }


}
