package com.devs.demoCours.api.models.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AlternativaRequest implements Serializable {
    private Long preguntaExamen;
    private String alternativa;
    private boolean valido;

}
