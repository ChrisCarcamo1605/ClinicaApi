package com.ApiREST.clinica.infra.security;

import com.ApiREST.clinica.domain.usuario.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Configuration
public class AutorizacionService implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        return usuarioRepository.findByUsername(username);
    }
}
