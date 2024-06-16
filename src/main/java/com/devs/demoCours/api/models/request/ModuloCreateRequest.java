package com.devs.demoCours.api.models.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ModuloCreateRequest implements Serializable {
    @NotNull(message = "el Id del curso es obligatorio")
    @Schema(example = "1")
    private Integer idCurso;
    @NotNull(message = "el nombre es obligatorio")
    @Schema(example = "nombre del modulo")
    private String nombre;
    @Schema(example = "detalle del curso")
    private String detalle;
}
