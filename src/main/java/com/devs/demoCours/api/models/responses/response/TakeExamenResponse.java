package com.devs.demoCours.api.models.responses.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class TakeExamenResponse implements Serializable {
    private Long idTakeExamen;
    private Float punctuation;
    private Integer intentos;

}
