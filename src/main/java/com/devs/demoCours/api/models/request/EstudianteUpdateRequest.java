package com.devs.demoCours.api.models.request;

import com.devs.demoCours.utils.Genero;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
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
    @NotNull(message = "El Id del estudiante es obligatorio")
    @Schema(example = "1")
    private Long id;
    @Size(min = 8,max = 8,message = "El DNI debe tener 8 caracteres")
    @NotNull(message = "El DNI es Obligatorio")
    @Schema(example = "70234343")
    private String dni;

    @Schema(example = "https:miAvatar.com")
    private String avatar;
    @Schema(example = "MASCULINO")
    private Genero genero;
    @Past(message = "La fecha de nacimiento no puede ser una fecha futura")
    @Schema(example = "1999-11-05")
    private LocalDate birthDate;
    @Schema(example = "Soy un estudiante con miras al futuro")
    private String review;

}
