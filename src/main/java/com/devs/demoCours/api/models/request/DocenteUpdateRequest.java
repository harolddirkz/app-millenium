package com.devs.demoCours.api.models.request;

import com.devs.demoCours.utils.Genero;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class DocenteUpdateRequest implements Serializable {
    private Long id;
    private String dni;
    private String avatar;
    private Genero genero;
    private LocalDate birthDate;
    private String especialidad;
    private String resenia;
}
