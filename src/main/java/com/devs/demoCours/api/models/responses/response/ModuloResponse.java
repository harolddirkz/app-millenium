package com.devs.demoCours.api.models.responses.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ModuloResponse implements Serializable {
    private Integer idModulo;
    private Integer numOrden;
    private String nombre;
    private String detalle;

}
