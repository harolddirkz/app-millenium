package com.devs.demoCours.domain.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;

@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity(name = "estudiante")
public class Estudiante extends Persona implements Serializable {
    @Column(name = "fecha_inscripcion",nullable = false,length = 10)
    private LocalDate fechaIncripcion;
    @Column(name = "resenia",length = 100)
    private String resenia;
}
