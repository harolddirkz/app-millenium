package com.devs.demoCours.domain.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

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
    /*
    Relacion con la entidad Sesion
     */
    @OneToMany(mappedBy = "docente",fetch = FetchType.LAZY,orphanRemoval = true)
    @JsonIgnore
    private List<Sesion> sesionList;


}
