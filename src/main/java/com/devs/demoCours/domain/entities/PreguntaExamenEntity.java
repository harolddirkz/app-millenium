package com.devs.demoCours.domain.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity(name = "pregunta_examen")
public class PreguntaExamenEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idPreguntaExamen;
    private String pregunta;

    /*
    Relación con la Entidad ExamenSession
     */
    @ManyToOne(cascade = CascadeType.PERSIST,optional = false)
    @JoinColumn(name = "examen_session_id")
    private ExamenSessionEntity examenSession;

    /*
    relación con la EntidadAlternativa;
    */
    @OneToMany(mappedBy = "preguntaExamen",cascade =  CascadeType.PERSIST,orphanRemoval = true)
    @JsonIgnore
    private List<AlternativaEntity> alternativaEntityList;
}
