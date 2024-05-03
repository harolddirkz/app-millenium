package com.devs.demoCours.domain.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class TakeExamen implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_take_examen")
    private Integer idTakeExamen;
    private Float puntuacion;
    private Integer intentos;

    //relacion con la Entidad Inscripcion
    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name = "inscripcion_id")
    private Inscripcion inscripcion;

    //relacion con la Entidad Examen
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "examen_sesion_id")
    private ExamenSesion examenSesion;

}
