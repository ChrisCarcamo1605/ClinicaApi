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
    private String username;
    private String password;
    String nombre;
    String correo;
    String documento;
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
        return username
                ;
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

        this.nombre = usuario.nombre();
        this.correo = usuario.correo();
        this.documento = usuario.documento();
        this.telefono = usuario.telefono();
        this.direccion = new Direccion(usuario.direccion().ciudad, usuario.direccion().calle, usuario.direccion().numero);
        this.activo = activo;
        return this;
    }

    public Usuario actualizarUsuario(DatosActualizarUsuario usuario) {
        this.nombre = usuario.nombre();
        this.correo = usuario.correo();
        this.telefono = usuario.telefono();
        this.documento = usuario.documento();
        this.direccion = new Direccion(usuario.direccion().calle, usuario.direccion().ciudad,
                usuario.direccion().numero);
        return this;
    }

    public void eliminarUsuario(Long id) {
        this.activo = false;

    }

}
