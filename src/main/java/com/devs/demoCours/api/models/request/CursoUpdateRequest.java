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
public class CursoUpdateRequest implements Serializable {
    @NotNull(message = "el id del curso es obligatorio")
    @Schema(example = "1")
    private Long id;
    @NotNull(message = "El nombre es obligatorio")
    @Schema(example = "Las nuevas Reformas Judiciales")
    private String nombre;
    @NotNull(message = "el tipo de curso es obligatorio")
    @Schema(example ="DIPLOMADO" )
    private TipoCurso tipoCurso;
    @Schema(example = "curso donde se verán las actualizaciones...",nullable = true)
    private String detalle;

    @NotNull(message = "se tiene que ingresar la modalidad del curso")
    @Schema(example = "ONLINE")
    private ModalidadCurso modalidadCurso;

    private boolean activo;
    private String image;
}
