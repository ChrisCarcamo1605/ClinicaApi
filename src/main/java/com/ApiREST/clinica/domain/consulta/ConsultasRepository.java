package com.ApiREST.clinica.domain.consulta;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.beans.JavaBean;
import java.util.List;

public interface ConsultasRepository extends JpaRepository<Consulta, Long> {




}


