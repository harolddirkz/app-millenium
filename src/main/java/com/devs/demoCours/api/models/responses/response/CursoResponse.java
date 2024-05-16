package com.devs.demoCours.api.models.responses.response;

import com.devs.demoCours.utils.ModalidadCurso;
import com.devs.demoCours.utils.TipoCurso;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@NoArgsConstructor
@AllArgsConstructor
@Data
public class CursoResponse {
    @Schema(example = "1")
    private Long id;
    @Schema(example = "derecho Laboral")
    private String nombre;
    @Schema(example = "ESPECIALIZACION")
    private TipoCurso tipoCurso;
    @Schema(example = "curso donde se verán las actualizaciones...")
    private String detalle;
    @Schema(example = "2024-10-02")
    private LocalDate fechaInicio;
    @Schema(example = "2024-11-02")
    private LocalDate fechaFin;
    @Schema(example = "PRESENCIAL")
    private ModalidadCurso modalidad;
    @Schema(example = "8")
    private Integer duration;
    //private List<SessionResponse> sessionEntityList;
}
