package com.ApiREST.clinica.domain.mascota;



import com.ApiREST.clinica.domain.usuario.Usuario;
import jakarta.persistence.*;
import lombok.*;


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
    private String id;
    private String nombre;
    @Enumerated(EnumType.STRING)
    TipoAnimal tipo;

    private Long dueño;

}
