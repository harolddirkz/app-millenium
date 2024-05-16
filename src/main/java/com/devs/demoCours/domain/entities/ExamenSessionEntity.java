package com.devs.demoCours.domain.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity(name = "examen_session")
public class ExamenSessionEntity implements Serializable {
    @Id@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_examen_session")
    private Integer idExamenSession;
    @Column(name = "detalles",length = 100,nullable = false)
    private String Detalles;
    @Column(name = "fecha_examen",length =10)
    private LocalDate fechaExamen;
    private Integer  duration;


    //relación con la Entidad TakeExamen
    @OneToMany(mappedBy = "examenSession",cascade = CascadeType.ALL,orphanRemoval = true)
    @JsonIgnore
    private List<TakeExamenEntity> takeExamenEntityList;
    //relación con la Entidad Session
    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name = "session_id")
    @JsonIgnore
    private SessionEntity session;
    /*
    relación con la entidad PreguntaExamen
     */
    @OneToMany(mappedBy = "examenSession",cascade = CascadeType.ALL,orphanRemoval = true)

    private List<PreguntaExamenEntity> preguntaExamenEntityList;

}
