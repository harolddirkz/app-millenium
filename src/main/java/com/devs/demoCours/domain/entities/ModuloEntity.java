package com.devs.demoCours.domain.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity(name = "modulo")
public class ModuloEntity implements Serializable {
    @Id
    @Column(name = "id-modulo")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idModulo;
    private Integer numOrden;
    private String nombre;
    private String detalle;
    /*relación con la entidad session*/
    @OneToMany(mappedBy = "modulo",cascade = CascadeType.ALL,orphanRemoval = true,fetch = FetchType.LAZY)
    private List<SessionEntity> sessionEntities;

    /*relación con la entidad Curso*/
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "curso_id")
    private CursoEntity curso;




}
