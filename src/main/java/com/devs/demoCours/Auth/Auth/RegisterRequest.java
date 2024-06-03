package com.devs.demoCours.Auth.Auth;

import com.devs.demoCours.utils.validacionesPersonalizadas.Dni;
import com.devs.demoCours.utils.validacionesPersonalizadas.NameOrLastName;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
    @Dni
    String dni;
    @NameOrLastName(message = "el nombre no debe contener números o caracteres especiales")
    String name;
    @NameOrLastName(message = "el apellido no debe contener números o caracteres especiales")
    String lastname;
    @Email
    String email;
    String password;


}
