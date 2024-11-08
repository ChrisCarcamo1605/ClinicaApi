package com.ApiREST.clinica.domain.usuario;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Page<Usuario> findByActivoTrue(Pageable pageable);
    UserDetails findByUsername(String username);
}

