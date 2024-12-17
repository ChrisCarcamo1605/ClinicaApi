package com.ApiREST.clinica.infra.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.preauth.AbstractPreAuthenticatedProcessingFilter;


@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

@Autowired
private SecurityFilter chain;


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        return http.csrf(httpSecurityCsrfConfigurer -> httpSecurityCsrfConfigurer.disable())
                .sessionManagement(ss-> {
                            try {
                                ss.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                                        .and().authorizeHttpRequests().requestMatchers(HttpMethod.POST,"/login").permitAll()
                                        .and().authorizeHttpRequests().requestMatchers(HttpMethod.POST,"/login/crearUsuario").permitAll()
                                        .and().authorizeHttpRequests().requestMatchers(HttpMethod.GET,"/swagger-ui/**").permitAll()
                                        .and().authorizeHttpRequests().requestMatchers(HttpMethod.GET,"/v3/api-docs/**").permitAll()
                                        .anyRequest()
                                        .authenticated()
                                        .and().addFilterBefore(chain, UsernamePasswordAuthenticationFilter.class);
                            } catch (Exception e) {
                                throw new RuntimeException(e);
                            }
                        }
                )
                .build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {

        return authenticationConfiguration.getAuthenticationManager();
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
