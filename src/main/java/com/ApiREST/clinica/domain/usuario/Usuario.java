package com.ApiREST.clinica.domain.usuario;


import com.ApiREST.clinica.domain.direccion.Direccion;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCrypt;

import java.util.Collection;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Table(name = "usuarios")
@Entity(name = "Usuario")
@EqualsAndHashCode(of = "id")
public class Usuario implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    String username;
    String password;
    String nombre;
    String correo;
    String dui;
    String telefono;
    @Embedded
    Direccion direccion;
    Boolean activo = true;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_USUARIO"));
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public Usuario guardarUsuario(DatosAgregarUsuario usuario) {
        this.username = usuario.correo();
        this.nombre = usuario.nombre();
        this.correo = usuario.correo();
        this.password = BCrypt.hashpw(usuario.password(), BCrypt.gensalt());
        this.dui = usuario.dui();
        this.telefono = usuario.telefono();
        this.direccion = new Direccion(usuario.direccion().calle, usuario.direccion().ciudad, usuario.direccion().colonia);
        this.activo = activo;
        return this;
    }

    public Usuario actualizarUsuario(DatosActualizarUsuario usuario) {

        if (usuario.telefono() != null) {
            this.telefono = usuario.telefono();
        }
        if (usuario.correo() != null) {
            this.correo = usuario.correo();
        }
        if (usuario.direccion() != null) {
            this.direccion = new Direccion(usuario.direccion().calle, usuario.direccion().ciudad, usuario.direccion().colonia);
        }
        if (usuario.nombre() != null) {
            this.nombre = usuario.nombre();
        }
        return this;
    }

    public void eliminarUsuario(Long id) {
        this.activo = false;

    }


}
