package com.devs.demoCours.domain.entities;

import com.devs.demoCours.utils.Genero;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
@MappedSuperclass
@Data
public class Persona {

    @Id
    @Column(name = "id_persona")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idPersona;
    @Column(nullable = false,unique = true,length =8 )
    private String dni;
    @Column(name = "nombre",nullable = false,length = 40)
    private String name;
    @Column(name = "apellidos",nullable = false,length = 60)
    private String lastName;
    @Column(unique = true,name = "correo")
    private String email;
    private String password;
    private String avatar;
    @Enumerated(EnumType.STRING)
    private Genero genero;
    private LocalDate birthDate;
    private boolean status;
}
