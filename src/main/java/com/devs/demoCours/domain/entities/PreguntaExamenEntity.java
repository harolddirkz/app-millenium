package com.devs.demoCours.domain.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity(name = "pregunta_examen")
public class PreguntaExamenEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idPreguntaExamen;
    private String pregunta;
    private Integer  duracion;
    private LocalDate fecha;

    /*
    Relacion con la Entidad ExamenSesion
     */
    @ManyToOne(cascade = CascadeType.ALL,optional = false)
    @JoinColumn(name = "examen_sesion_id")
    private ExamenSesionEntity examenSesion;

    /*
    relacion con la EntidadAlternativa;
    */
    @OneToMany(mappedBy = "preguntaExamen",cascade =  CascadeType.ALL,orphanRemoval = true)
    @JsonIgnore
    private List<AlternativaEntity> alternativaEntityList;
}
