package com.devs.demoCours.api.models.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@NoArgsConstructor
@AllArgsConstructor
@Data
public class SessionUpdateRequest {
    @NotNull(message = "el Id de la sesión es obligatorio")
    @Schema(example = "1")
    private Long id;
    @NotNull(message = "el Id del docente es obligatorio")
    @Schema(example = "2")
    private Long idDocente;
    @NotNull(message = "la descripción es obligatorio")
    @NotBlank(message = "tiene que ingresar la descripción ")
    @Schema(example = "este tema se tratará sobre los derechos ...")
    private String description;
    private String material;
    @NotNull(message = "el status del curso es obligatorio")
    @Schema(example = "true")
    private boolean status;
    @NotNull(message = "ingrese la fecha de inicio")
    @Schema(example = "1")
    private LocalDateTime inicioSession;
    @NotNull(message = "ingrese la fecha de culminación")
    @Schema(example = "1")
    private LocalDateTime finalSession;
}
