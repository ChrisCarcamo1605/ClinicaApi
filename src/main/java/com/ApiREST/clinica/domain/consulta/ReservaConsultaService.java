package com.ApiREST.clinica.domain.consulta;


import com.ApiREST.clinica.domain.mascota.Mascota;
import com.ApiREST.clinica.domain.mascota.MascotaRepository;
import com.ApiREST.clinica.domain.usuario.UsuarioRepository;
import com.ApiREST.clinica.domain.veterinario.Veterinario;
import com.ApiREST.clinica.domain.veterinario.VeterinarioRepository;
import com.ApiREST.clinica.infra.errores.ValidacionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class ReservaConsultaService {
    @Autowired
    ConsultasRepository consultasRepository;

    @Autowired
    VeterinarioRepository veterinarioRepository;

    @Autowired
    MascotaRepository mascotaRepository;
    @Autowired
    UsuarioRepository usuarioRepository;

    public Consulta guardarConsulta(DatosReservaConsulta dtoConsulta) {

        if (!usuarioRepository.existsById(dtoConsulta.idUsuario())) {
            throw new ValidacionException("El id no hace referencia a ningun usuario");
        }
        if (!veterinarioRepository.existsById(dtoConsulta.idVeterinario())) {
            throw new ValidacionException("El id no hace referencia a ningun medicos");
        }

        var veterinario = elegirVeterinario(dtoConsulta);
        var usuario = usuarioRepository.findById(dtoConsulta.idUsuario()).get();
        var mascota = mascotaRepository.findById(dtoConsulta.idMascota()).get();
        return consultasRepository.save(new Consulta(usuario, veterinario, dtoConsulta.fecha(), mascota, true));

    }

    private Veterinario elegirVeterinario(DatosReservaConsulta dtoConsulta) {

        if (dtoConsulta.idVeterinario() == null) {
            return veterinarioRepository.elegirVeterinarioDisponible(dtoConsulta.especialidad(), dtoConsulta.fecha());
        }
        if (dtoConsulta.especialidad() == null) {
            throw new ValidacionException("Elija una especialidad");
        }
        var veterinario = veterinarioRepository.findById(dtoConsulta.idVeterinario()).get();

        return veterinario;

    }

    public void cancelarConsulta(DatosCancelarConsulta dto) {
        if (!usuarioRepository.existsById(dto.id())) {

            throw new ValidacionException("El id no hace referencia a ninguna consulta");
        }

        Consulta consulta = consultasRepository.getReferenceById(dto.id());
        consulta.cancelarConsulta(dto.motivoCancelamiento());
    }
}
