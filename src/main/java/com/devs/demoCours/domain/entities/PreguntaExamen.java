package com.devs.demoCours.domain.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity(name = "pregunta_examen")
public class PreguntaExamen {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idPreguntaExamen;
    private String pregunta;
    private Integer  duracion;
    private LocalDate fecha;
}
