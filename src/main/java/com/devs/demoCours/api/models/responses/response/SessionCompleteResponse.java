package com.devs.demoCours.api.models.responses.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class SessionCompleteResponse {
    private Long id;
    private Integer numOrden;
    private LocalDateTime inicioSession;
    private LocalDateTime finalSession;
    private String material;
    private long duration;
    private boolean status;
    //private CursoResponse curso;
    private String description;
    private DocenteResponse docente;
}
