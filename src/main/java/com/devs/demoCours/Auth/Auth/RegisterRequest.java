package com.devs.demoCours.Auth.Auth;

import com.devs.demoCours.utils.validacionesPersonalizadas.Dni;
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
    String name;
    String lastname;
    @Email
    String email;
    String password;


}
