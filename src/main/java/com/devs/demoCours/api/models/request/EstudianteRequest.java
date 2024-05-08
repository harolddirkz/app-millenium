package com.devs.demoCours.api.models.request;

import com.devs.demoCours.utils.Genero;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class EstudianteRequest implements Serializable {
    private Integer id;

    private String dni;
    private String name;
    private String lastName;
    private String email;
    private String avatar;
    private Genero genero;
    private LocalDate birthDate;
    private String resenia;

}
