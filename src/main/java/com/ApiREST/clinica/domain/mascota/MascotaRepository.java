package com.ApiREST.clinica.domain.mascota;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MascotaRepository extends JpaRepository<Mascota, Long> {
    List<Mascota> findByDueño_Id(Long idDueño);

}
