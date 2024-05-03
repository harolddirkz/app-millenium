package com.devs.demoCours.domain.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity(name = "sesion")
public class Sesion implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_sesion")
    private Integer idSesion;
    private Integer numOrden;
    private LocalDateTime inicioSesion;
    private LocalDateTime finalSesion;
    private boolean status;
    private String descripcion;
    private String material;

    //relacion con la Entidad ExamenSesion
    @OneToMany(mappedBy = "sesion",cascade = CascadeType.ALL,orphanRemoval = true)
    @JsonIgnore
    private List<ExamenSesion> examenSesionList;

    //relacion con la entidad Curso
    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name = "curso_id")
    private Curso curso;
    /*
    relacion con la Entidad Docente
     */
    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name = "docente_id")
    private Docente docente;
    /*
    Relacion con la entidad MaterialEducativo
     */
    @OneToMany(mappedBy = "sesion",cascade = CascadeType.ALL,orphanRemoval = true)
    @JsonIgnore
    private List<MaterialEducativo> materialEducativoList;


}
