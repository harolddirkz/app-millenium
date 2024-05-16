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
@Entity(name = "session")
@Builder
public class SessionEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_session")
    private Integer idSession;
    private Integer numOrden;
    private LocalDateTime inicioSession;
    private LocalDateTime finalSession;
    private boolean status;
    private String description;
    private String material;

    //relación con la Entidad ExamenSession
    @OneToMany(mappedBy = "session",cascade = CascadeType.ALL,orphanRemoval = true,fetch = FetchType.LAZY)
    @JsonIgnore
    private List<ExamenSessionEntity> examenSessionEntityList;

    //relación con la entidad Curso
    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name = "curso_id")
    private CursoEntity curso;
    /*
    relación con la Entidad Docente
     */
    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name = "docente_id")

    private DocenteEntity docente;
    /*
    Relación con la entidad MaterialEducativo
     */
    @OneToMany(mappedBy = "session",cascade = CascadeType.ALL,orphanRemoval = true)
    @JsonIgnore
    private List<MaterialEducativoEntity> materialEducativoEntityList;


}
