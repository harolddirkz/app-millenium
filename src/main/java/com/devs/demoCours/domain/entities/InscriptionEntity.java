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
@Entity(name = "inscription")
public class InscriptionEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_Inscription")
    private Integer idInscription;
    private LocalDateTime fechaInscription;
    private boolean estado;
    private Float qualification;

    /*
    * relación con la Entidad TakeExamen
     */
    @OneToMany(mappedBy = "inscription",cascade = CascadeType.ALL,orphanRemoval = true)
    @JsonIgnore
    private List<TakeExamenEntity> takeExamEntities;
    /*
    * relación con la entidad Curso
     */
    @ManyToOne(fetch = FetchType.EAGER,optional = false)
    @JoinColumn(name = "curso_id")

    private CursoEntity curso;

    /*
    relación con la entidad Estudiante
     */
    @ManyToOne(fetch = FetchType.EAGER,optional = false)
    @JoinColumn(name = "estudiante_id")
    private EstudianteEntity estudiante;




}
