package com.ApiREST.clinica.domain.medico;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;


public interface MedicoRepository extends JpaRepository<Medico, Long> {
    Page<Medico> findByActivoTrue(Pageable pageable);


    @Query("""
                select m from Medico m where m.especialidad = :especialidad and m.activo = true 
                and m.id not in(select m.id from Medico where m.fecha =:fecha )
                order by rand() limit 1
            """)
    Medico elegirVeterinarioDisponible(Especialidad especialidad, LocalDateTime fecha);

    @Query("""
                select m from Medico m where m.activo = true 
                and m.id not in(select m.id from Medico where m.fecha =:fecha )
                order by rand() limit 1
            """)
    Medico elegirVeterinarioDisponible(LocalDateTime fecha);

}
