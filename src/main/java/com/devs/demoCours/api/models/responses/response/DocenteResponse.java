package com.devs.demoCours.api.models.responses.response;

import com.devs.demoCours.utils.Genero;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class DocenteResponse {
    private Long id;
    private String dni;
    private String name;
    private String lastName;
    private String email;
    private String especialidad;
    private String avatar;
    private Genero genero;
    private String resenia;
}
