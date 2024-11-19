package com.ApiREST.clinica.domain.veterinario;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;


public interface VeterinarioRepository extends JpaRepository<Veterinario, Long> {
    Page<Veterinario> findByActivoTrue(Pageable pageable);
    public List<Veterinario> findVeterinarioById(Long id);
    Optional<Veterinario> findByCorreo(String email);
    List<Veterinario> findByEspecialidad(String especialidad);
    List<Veterinario> findByActivoTrue();



    List<Veterinario> findByActivoAndHora(Boolean activo, LocalTime hora);



    @Query("SELECT v FROM Veterinario v WHERE v.id NOT IN " +
            "(SELECT c.veterinario.id FROM Consulta c WHERE c.fecha = :fecha)")
    List<Veterinario> findByActivo(@Param("fecha") LocalDate fecha);
}

