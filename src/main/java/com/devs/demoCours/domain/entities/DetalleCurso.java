package com.devs.demoCours.domain.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity(name = "Detalle_curso")
public class DetalleCurso implements Serializable {
    @Id
    @Column(name = "detalle_curso_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer IdDetalleCurso;


    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name = "id_curso")
    @JsonIgnore
    private Curso curso;
}
