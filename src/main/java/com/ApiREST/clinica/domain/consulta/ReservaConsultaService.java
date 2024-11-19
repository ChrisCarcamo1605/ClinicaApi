package com.ApiREST.clinica.domain.consulta;


import com.ApiREST.clinica.domain.mascota.Mascota;
import com.ApiREST.clinica.domain.mascota.MascotaRepository;
import com.ApiREST.clinica.domain.usuario.Usuario;
import com.ApiREST.clinica.domain.usuario.UsuarioRepository;
import com.ApiREST.clinica.domain.veterinario.Veterinario;
import com.ApiREST.clinica.domain.veterinario.VeterinarioRepository;
import com.ApiREST.clinica.infra.errores.ValidacionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
@Transactional
public class ReservaConsultaService {
    @Autowired
    ConsultasRepository consultasRepository;

    @Autowired
    VeterinarioRepository veterinarioRepository;

    @Autowired
    MascotaRepository mascotaRepository;
    @Autowired
    UsuarioRepository usuarioRepository;

    public Consulta guardarConsulta(DatosReservaConsulta dto, LocalDate fecha) {
        // Validar que los datos de reserva no sean nulos
        if (dto == null) {
            throw new ValidacionException("Los datos de reserva no pueden ser nulos.");
        }

        // Elegir el veterinario según la lógica de tu aplicación
        Veterinario veterinario = elegirVeterinario(dto, fecha);

        // Asignar una hora disponible para la cita
        LocalTime horaAsignada = asignarHoraCita(fecha, veterinario);

        // Obtener el usuario a partir del ID proporcionado en el DTO
        Usuario usuario = usuarioRepository.findById(dto.idUsuario())
                .orElseThrow(() -> new ValidacionException("Usuario no encontrado"));

        // Obtener la mascota a partir del ID proporcionado en el DTO
        Mascota mascota = mascotaRepository.findById(dto.idMascota())
                .orElseThrow(() -> new ValidacionException("Mascota no encontrada"));

        // Crear una nueva consulta con los datos obtenidos
        Consulta nuevaConsulta = new Consulta(usuario, veterinario, fecha, horaAsignada, mascota, true);

        // Imprimir el ID del usuario para depuración
        System.out.println("ID USUARIO PA: " + nuevaConsulta.getUsuario().getId());

        // Guardar la nueva consulta en el repositorio
        return consultasRepository.save(nuevaConsulta);
    }


    // Método para elegir veterinario
    private Veterinario elegirVeterinario(DatosReservaConsulta dto, LocalDate fecha) {
        List<Veterinario> veterinariosDisponibles = veterinarioRepository.findByActivo(fecha);

        return veterinariosDisponibles.stream()
                .findFirst()
                .orElseThrow(() -> new ValidacionException("No hay veterinarios disponibles para esta fecha."));
    }


    public LocalTime asignarHoraCita(LocalDate fecha, Veterinario veterinario) {
        List<LocalTime> horasDisponibles = generarHorasDisponibles();
        List<Consulta> citasExistentes = consultasRepository.findByFechaAndVeterinario(fecha, veterinario);
        Set<LocalTime> horasOcupadas = new HashSet<>();
        for (Consulta cita : citasExistentes) {
            horasOcupadas.add(cita.getHora());
        }
        List<LocalTime> horasFiltradas = horasDisponibles.stream()
                .filter(hora -> !horasOcupadas.contains(hora))
                .collect(Collectors.toList());
        if (!horasFiltradas.isEmpty()) {
            return horasFiltradas.get(0);
        } else {
            System.out.println("No hay horas disponibles para la fecha seleccionada.");
            return null;
        }
    }

    public List<LocalTime> generarHorasDisponibles() {
        List<LocalTime> horasDisponibles = new ArrayList<>();
        LocalTime horaInicio = LocalTime.of(8, 0);
        LocalTime horaFin = LocalTime.of(18, 0);
        LocalTime horaActual = horaInicio;

        while (horaActual.isBefore(horaFin) || horaActual.equals(horaFin)) {
            horasDisponibles.add(horaActual);
            horaActual = horaActual.plusMinutes(30);
        }

        return horasDisponibles;
    }


}


