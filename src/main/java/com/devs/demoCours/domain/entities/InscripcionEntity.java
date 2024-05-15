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
@Builder
@Entity(name = "inscripcion")
public class InscripcionEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_Descripcion")
    private Integer idInscripcion;
    private LocalDateTime fechaInscripcion;
    private boolean estado;
    private Float calificacion;

    /*
    * relacion con la Entidad TakeExamen
     */
    @OneToMany(mappedBy = "inscripcion",cascade = CascadeType.ALL,orphanRemoval = true)
    @JsonIgnore
    private List<TakeExamenEntity> takeExamanEntities;
    /*
    * relacion con la entidad Curso
     */
    @ManyToOne(fetch = FetchType.EAGER,optional = false)
    @JoinColumn(name = "curso_id")

    private CursoEntity curso;

    /*
    relacion con la entidad Estudiante
     */
    @ManyToOne(fetch = FetchType.EAGER,optional = false)
    @JoinColumn(name = "estudiante_id")
    private EstudianteEntity estudiante;




}
