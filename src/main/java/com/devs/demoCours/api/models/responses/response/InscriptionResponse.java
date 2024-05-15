package com.devs.demoCours.api.models.responses.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;
@NoArgsConstructor
@AllArgsConstructor
@Data
public class InscriptionResponse implements Serializable {
    private Long idInscripcion;
    private LocalDateTime fechaInscripcion;
    private CursoResponse curso;
}
