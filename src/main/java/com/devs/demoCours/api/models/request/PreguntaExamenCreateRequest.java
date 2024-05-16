package com.devs.demoCours.api.models.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PreguntaExamenCreateRequest implements Serializable {
    private Long idExamenSession;
    private String pregunta;
}
