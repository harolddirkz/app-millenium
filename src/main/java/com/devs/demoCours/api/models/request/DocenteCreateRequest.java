package com.devs.demoCours.api.models.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
@NoArgsConstructor
@AllArgsConstructor
@Data
public class DocenteCreateRequest implements Serializable {
    @Size(min = 8,max = 8,message = "El DNI debe tener 8 dígitos")
    @NotNull(message = "El DNI es obligatorio")
    @Schema(example = "70370695")

    private String dni;

    @NotNull(message = "El Nombre es obligatorio")
    @Schema(example = "Juan")
    @NotBlank(message = "el nombre no puede ser vació")
    private String name;

    @NotNull(message = "El Apellido es obligatorio")
    @Schema(example = "Torres Canales")
    @NotBlank(message = "el apellido no puede ser vació")
    private String lastName;

    @NotNull(message = "El correo es obligatorio")
    @Email(message = "El formato del email no es valido")
    @Schema(example = "docente@example.com")
    private String email;
    @NotNull(message = "La Contraseña es obligatorio")
    @Size(min = 7,max = 15,message = "La Contraseña debe tener mas de 8 caracteres")
    @Schema(example = "aq@drrusQWER")
    private String password;

    @NotNull(message = "La Especialidad es obligatorio")
    @Schema(example = "Abogado")
    @NotBlank(message = "la especialidad no puede ser vació")
    private String especialidad;

}
