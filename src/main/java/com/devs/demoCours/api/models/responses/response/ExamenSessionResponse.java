package com.devs.demoCours.api.models.responses.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ExamenSessionResponse implements Serializable {
    private Long idExamenSession;
    private LocalDate fechaExamen;
    private Integer duration;
    private List<PreguntaExamenResponse> preguntaExamenEntityList;
}
