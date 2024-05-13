package com.devs.demoCours.domain.entities;

import com.devs.demoCours.utils.ModalidadCurso;
import com.devs.demoCours.utils.TipoCurso;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity(name = "curso")
public class CursoEntity implements Serializable {
    @Id
    @Column(name = "id_curso")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idCurso;
    @Column(name = "nombre_curso", nullable = false,length = 100)
    private String nombre;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_curso",nullable = false)
    private TipoCurso tipoCurso;
    @Column(name = "detalle_curso", length = 500)
    private String detalle;
    @Column(name = "fecha_de_inicio", length = 10)
    private LocalDate fechaInicio;
    @Column(name = "fecha_de_culminacion", length = 10)
    private LocalDate fechaFin;

    @Enumerated(EnumType.STRING)
    @Column(name = "modalidad",nullable = false)
    private ModalidadCurso modalidad;
    @Column(name = "duracion",nullable = false)
    private Integer duration;
    @Column(name = "activo")
    private boolean activo;

    /*
    * relación con la Entidad Detalle-Curso
    */
    @OneToMany(mappedBy = "curso",cascade = CascadeType.ALL,orphanRemoval = true)
    @JsonIgnore
    private Set<DetalleCursoEntity> detalleCurso;

    /*
    * relación con la entidad Sesión
    */
    @OneToMany(mappedBy = "curso",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<SesionEntity> sesionEntityList;
    /*
    Relación con la Entidad Inscripción
     */
    @OneToMany(mappedBy = "curso",fetch = FetchType.LAZY,orphanRemoval = true)
    private List<InscripcionEntity> inscripcionEntities;
}
