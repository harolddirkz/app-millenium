package com.devs.demoCours.domain.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

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

    /*
    relacion con la entidad Inscripcion
     */
    @OneToMany(mappedBy = "estudiante",fetch = FetchType.LAZY,orphanRemoval = true)
    @JsonIgnore
    private List<Inscripcion> inscripcionList;

}
