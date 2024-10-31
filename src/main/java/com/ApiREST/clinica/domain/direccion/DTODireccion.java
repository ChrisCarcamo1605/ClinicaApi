package com.ApiREST.clinica.domain.direccion;

import jakarta.validation.constraints.NotNull;

public record DTODireccion(
                @NotNull
                String calle,
                @NotNull
                String ciudad,
                @NotNull
                String numero) {
}
