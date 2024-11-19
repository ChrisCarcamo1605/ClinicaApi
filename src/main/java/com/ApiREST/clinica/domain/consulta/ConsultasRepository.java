package com.ApiREST.clinica.domain.consulta;

import com.ApiREST.clinica.domain.veterinario.Veterinario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.beans.JavaBean;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public interface ConsultasRepository extends JpaRepository<Consulta, Long> {
    List<Consulta> findByFechaAndVeterinario(LocalDate fecha, Veterinario veterinario);
}


