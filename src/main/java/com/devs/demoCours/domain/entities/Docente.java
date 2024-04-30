package com.devs.demoCours.domain.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity(name = "docente")
public class Docente extends Persona implements Serializable {
    @Column(name = "especialidad_docente", length = 40)
    private String especialida;
    @Column(name = "resenia", length = 500)
    private String resenia;


}
