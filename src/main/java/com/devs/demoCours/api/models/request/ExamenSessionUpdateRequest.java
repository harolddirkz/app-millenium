package com.devs.demoCours.api.models.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ExamenSessionUpdateRequest {
    private Long id;
    private String detalles;
    private LocalDate fechaExamen;
    private Long idSession;
    private Integer duration;
}
