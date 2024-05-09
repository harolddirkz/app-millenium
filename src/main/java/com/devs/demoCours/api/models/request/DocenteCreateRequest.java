package com.devs.demoCours.api.models.request;

import com.devs.demoCours.utils.Genero;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
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
    @NotBlank(message = "El DNI es obligatorio")
    private String dni;
    @NotBlank(message = "El Nombre es obligatorio")
    private String name;
    @NotBlank(message = "El Apellido es obligatorio")
    private String lastName;
    @NotBlank(message = "El correo es obligatorio")
    @Email(message = "El formato del email no es valido")
    private String email;
    @NotBlank(message = "La Contraseña es obligatorio")
    @Size(min = 7,message = "La Contraseña debe tener mas de 8 caracteres")
    private String password;
    @NotBlank(message = "La Especialidad es obligatorio")
    private String especialidad;
}
