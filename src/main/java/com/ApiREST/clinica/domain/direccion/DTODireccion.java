package com.ApiREST.clinica.domain.direccion;

import jakarta.validation.constraints.NotNull;

public record DTODireccion(

                String calle,

                String ciudad,

                String colonia) {
}
