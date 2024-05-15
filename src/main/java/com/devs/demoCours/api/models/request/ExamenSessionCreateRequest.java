package com.devs.demoCours.api.models.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ExamenSessionCreateRequest implements Serializable {

    private String detalles;
    private LocalDate fechaExamen;
    private Long idSession;



}
