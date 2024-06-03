package com.devs.demoCours.api.models.request;

import com.devs.demoCours.utils.ModalidadCurso;
import com.devs.demoCours.utils.TipoCurso;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CursoCreateRequest implements Serializable {
    @NotNull(message = "El nombre es obligatorio")
    @NotBlank(message = "nombre no puede ser vació")
    @Schema(example = "Las nuevas Reformas Judiciales")
    private String nombre;
    @Schema(example = "DIPLOMADO")
    @NotNull(message = "el tipo de curso es obligatorio")
    private TipoCurso tipoCurso;

    @Schema(example = "curso donde se verán las actualizaciones...", nullable = true)
    private String detalle;

    @FutureOrPresent(message = "La fecha de inicio del curso no puede ser una fecha pasada")
    @Schema(example = "2025-10-02", nullable = true)
    private LocalDate fechaInicio;

    @Future(message = "la fecha de finalización del curso no puede ser una fecha pasada")
    @Schema(example = "2025-11-02", nullable = true)
    private LocalDate fechaFin;

    @NotNull(message = "se tiene que ingresar la modalidad del curso")
    @Schema(example = "ONLINE")
    private ModalidadCurso modalidadCurso;

    @NotNull(message = "se tiene que ingresar la duración del curso en horas")
    @Positive(message = "la horas no pueden ser negativas")
    @Schema(example = "6")
    private Integer duration;
    private boolean activo;
    private String image;


}
