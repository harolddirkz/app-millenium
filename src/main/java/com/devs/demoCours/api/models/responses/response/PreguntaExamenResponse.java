package com.devs.demoCours.api.models.responses.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PreguntaExamenResponse implements Serializable {
    private Long idPreguntaExamen;
    private String pregunta;
    private List<AlternativaResponse> alternativaEntityList;

}
