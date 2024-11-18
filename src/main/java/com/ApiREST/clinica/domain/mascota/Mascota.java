package com.ApiREST.clinica.domain.mascota;


import com.ApiREST.clinica.domain.usuario.Usuario;
import com.ApiREST.clinica.domain.usuario.UsuarioRepository;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;


@Table(name = "Mascotas")
@Entity(name = "Mascota")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Mascota {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    @Enumerated(EnumType.STRING)
    TipoAnimal tipoanimal;

    @JoinColumn(name = "iddueño")
    @ManyToOne(fetch = FetchType.LAZY)
    private Usuario dueño;


    public Mascota(Usuario dueño, String nombre, TipoAnimal tipoanimal) {
        this.dueño = dueño;
        this.nombre = nombre;
        this.tipoanimal = tipoanimal;

    }

}
