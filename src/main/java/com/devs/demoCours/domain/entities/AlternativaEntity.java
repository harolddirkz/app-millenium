package com.devs.demoCours.domain.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity(name = "alternativa")
public class AlternativaEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_alternativa")
    private Integer idAlternativa;
    @Column(name = "alternativa",nullable = false)
    private String alternativa;
    @Column(name = "valido",nullable = false)
    private boolean valido;
    /*
    Relación con La Entidad PreguntaExamen
     */
    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name = "pregunta_examen_id")
    private PreguntaExamenEntity preguntaExamen;

}
