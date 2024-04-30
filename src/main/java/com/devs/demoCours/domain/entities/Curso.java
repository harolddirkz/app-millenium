package com.devs.demoCours.domain.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity(name = "curso")
public class Curso implements Serializable {
    @Id
    @Column(name = "id_curso")
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Integer idCurso;

    @Column(name = "nombre_curso", nullable = false,length = 100)
    private String nombre;
    private String tipoCurso;
    @Column(name = "detalle_curso", length = 500)
    private String detalle;
    @Column(name = "fecha_de_inicio", length = 10)
    private LocalDate fechaInicio;
    @Column(name = "fecha_de_culminacion", length = 10)
    private LocalDate fechaFin;
    private String horaDictado;
    private String modalidad;
    private Integer duracion;
    private Boolean estado;

    @OneToMany(mappedBy = "curso")
    @JsonIgnore
    private Set<DetalleCurso> detalle_curso;
}
