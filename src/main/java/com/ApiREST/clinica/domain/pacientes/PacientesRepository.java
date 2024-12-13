package com.ApiREST.clinica.domain.pacientes;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface PacientesRepository extends JpaRepository<Paciente,Long> {

    Page<Paciente> findByActivoTrue(Pageable pageable);
}
