package com.devs.demoCours.domain.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity(name = "examen_sesion")
public class ExamenSesion implements Serializable {
    @Id@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_examen_sesion")
    private Integer idExamenSesion;
    @Column(name = "detalles",length = 100,nullable = false)
    private String Detalles;
    @Column(name = "fecha_examen",length =10)
    private LocalDate fechaExamen;


    //relacion con la Entidad TakeExamen
    @OneToMany(mappedBy = "examenSesion",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<TakeExamen> takeExamenList;
    //relacion con la Entidad Sesion
    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name = "sesion_id")
    @JsonIgnore
    private Sesion sesion;

}
