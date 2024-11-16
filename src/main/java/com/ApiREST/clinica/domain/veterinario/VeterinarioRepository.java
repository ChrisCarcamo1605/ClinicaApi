package com.ApiREST.clinica.domain.veterinario;

import com.ApiREST.clinica.domain.consulta.Consulta;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;


public interface VeterinarioRepository extends JpaRepository<Veterinario, Long> {
    Page<Veterinario> findByActivoTrue(Pageable pageable);
    public List<Veterinario> findVeterinarioById(Long id);


    @Query("""
            select v from Veterinario v 
            where v.activo = true and 
            v.especialidad = :especialidad and
            v.id not in(select v.id from Veterinario v 
            where v.fecha = :fecha)
            order by rand()
            limit 1""")
    Veterinario elegirVeterinarioDisponible(Especialidad especialidad, LocalDateTime fecha);
}
