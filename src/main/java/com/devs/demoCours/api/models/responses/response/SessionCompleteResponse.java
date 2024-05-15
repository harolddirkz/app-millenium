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
    private LocalDateTime inicioSesion;
    private LocalDateTime finalSesion;
    private String material;
    //private CursoResponse curso;
    private String descripcion;
    private DocenteResponse docente;
}
