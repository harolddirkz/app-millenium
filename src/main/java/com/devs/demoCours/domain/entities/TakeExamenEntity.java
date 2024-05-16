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
@Entity
public class TakeExamenEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_take_examen")
    private Integer idTakeExamen;
    private Float punctuation;
    private Integer intentos;

    //relación con la Entidad Inscripción
    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name = "inscription_id")
    private InscriptionEntity inscription;

    //relación con la Entidad Examen
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "examen_session_id")
    private ExamenSessionEntity examenSession;

}
