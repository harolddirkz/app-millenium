package com.devs.demoCours.domain.entities;

import com.devs.demoCours.utils.Genero;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.util.List;

@MappedSuperclass
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class Persona {

    @Id
    @Column(name = "id_persona")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Integer idPersona;
    @Column(nullable = false,unique = true,length =8 )
    protected String dni;
    @Column(name = "nombre",nullable = false,length = 40)
    protected String name;
    @Column(name = "apellidos",nullable = false,length = 60)
    protected String lastName;
    @Column(unique = true,name = "correo")
    protected String email;
    protected String password;
    protected String avatar;
    @Enumerated(EnumType.STRING)
    protected Genero genero;
    @Column(name = "fecha_nacimiento",length = 10)
    protected LocalDate birthDate;
    protected boolean status;


}
