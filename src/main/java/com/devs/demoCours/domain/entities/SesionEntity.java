package com.devs.demoCours.domain.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity(name = "sesion")
@Builder
public class SesionEntity implements Serializable {
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
    @OneToMany(mappedBy = "sesion",cascade = CascadeType.ALL,orphanRemoval = true,fetch = FetchType.LAZY)
    @JsonIgnore
    private List<ExamenSesionEntity> examenSesionEntityList;

    //relacion con la entidad Curso
    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name = "curso_id")
    private CursoEntity curso;
    /*
    relacion con la Entidad Docente
     */
    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name = "docente_id")

    private DocenteEntity docente;
    /*
    Relacion con la entidad MaterialEducativo
     */
    @OneToMany(mappedBy = "sesion",cascade = CascadeType.ALL,orphanRemoval = true)
    @JsonIgnore
    private List<MaterialEducativoEntity> materialEducativoEntityList;


}
