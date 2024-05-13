package com.devs.demoCours.api.models.request;

import com.devs.demoCours.utils.Genero;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
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
public class DocenteUpdateRequest implements Serializable {
    @NotNull(message = "El Id del docente es obligatorio")
    @Schema(example = "1")
    private Long id;
    @Size(min = 8,max = 8,message = "El DNI debe tener 8 caracteres")
    @NotNull(message = "El DNI del docente es obligatorio")
    @Schema(example = "70370695")
    private String dni;
    @Schema(example = "https:miAvatar.com",nullable = true)

    private String avatar;
    @Schema(example = "MASCULINO",nullable = true)
    private Genero genero;
    @Schema(example = "1995-12-01",nullable = true)
    @Past(message = "fecha de nacimiento no puede ser fecha futura")
    private LocalDate birthDate;
    @Schema(example = "Abogado",nullable = true)
    private String especialidad;
    @Schema(example = "Abogado con 20 años de experiencia...",nullable = true)
    private String resenia;
}
