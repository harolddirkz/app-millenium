package com.devs.demoCours.api.models.responses.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@NoArgsConstructor
@AllArgsConstructor
@Data
public class SessionResponse {
    private Integer numOrden;
    private LocalDateTime inicioSesion;
    private LocalDateTime finalSesion;
    private String descripcion;
    private DocenteResponse docente;

}
