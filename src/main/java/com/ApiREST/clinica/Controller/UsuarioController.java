package com.ApiREST.clinica.Controller;

import com.ApiREST.clinica.domain.direccion.Direccion;
import com.ApiREST.clinica.domain.usuario.*;
import com.ApiREST.clinica.infra.security.TokenService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;


@RestController
@RequestMapping("/usuario")
public class UsuarioController {
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    TokenService tokenService;
    @Autowired
    UsuarioRepository usuariosRepository;




//    @PostMapping
//    public ResponseEntity<DatosRespuestaUsuario> registrarUsuario(@RequestBody @Valid DatosAgregarUsuario datosAgregarPaciente,
//                                                                  UriComponentsBuilder uriComponentsBuilder) {
//
//        Usuario usuario = usuariosRepository.save(new Usuario().guardarUsuario(datosAgregarPaciente));
//
//        DatosRespuestaUsuario datosRespuestaPaciente = new DatosRespuestaUsuario(datosAgregarPaciente.nombre()
//                , datosAgregarPaciente.correo(), datosAgregarPaciente.telefono(), datosAgregarPaciente.dui(),
//                new Direccion(datosAgregarPaciente.direccion().calle,
//                        datosAgregarPaciente.direccion().ciudad, datosAgregarPaciente.direccion().colonia));
//
//        URI url = uriComponentsBuilder.path("/paciente").build().toUri();
//        return ResponseEntity.created(url).body(datosRespuestaPaciente);
//    }

    @GetMapping
    public ResponseEntity<Page<DatosListadoUsuario>> listarUsuarios(@PageableDefault(size = 3) Pageable pageable) {
        return ResponseEntity.ok(usuariosRepository.findByActivoTrue(pageable).map(DatosListadoUsuario::new));
    }

    @PutMapping
    @Transactional
    public ResponseEntity<DatosRespuestaUsuario> actualizarUsuarios(@RequestBody @Valid DatosActualizarUsuario dto) {
        Usuario usuario = usuariosRepository.save(new Usuario().actualizarUsuario(dto));
        usuario.actualizarUsuario(dto);

        return ResponseEntity.ok(new DatosRespuestaUsuario(dto.nombre(),
                dto.correo(), dto.telefono(),
                dto.dui(), new Direccion(dto.direccion().calle,
                dto.direccion().ciudad, dto.direccion().colonia)));

    }


    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity eliminarUsuarios(@PathVariable Long id) {
        Usuario usuario = usuariosRepository.getReferenceById(id);
        usuario.eliminarUsuario(id);
        return ResponseEntity.noContent().build();
    }
}




