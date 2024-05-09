package com.devs.demoCours.api.models.request;

import com.devs.demoCours.utils.Genero;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class EstudianteUpdateRequest implements Serializable {
    @NotBlank(message = "El Id del docente es obligatorio")
    private Long id;
    @Size(min = 8,max = 8,message = "El DNI debe tener 8 caracteres")
    @NotBlank(message = "El DNI es Obligatorio")
    private String dni;
    private String avatar;
    private Genero genero;
    private LocalDate birthDate;
    private String resenia;

}
