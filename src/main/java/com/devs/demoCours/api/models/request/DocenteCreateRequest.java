package com.devs.demoCours.api.models.request;

import com.devs.demoCours.utils.Genero;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
@NoArgsConstructor
@AllArgsConstructor
@Data
public class DocenteCreateRequest implements Serializable {
    private String dni;
    private String name;
    private String lastName;
    private String email;
    private String password;
    private String especialidad;
}
