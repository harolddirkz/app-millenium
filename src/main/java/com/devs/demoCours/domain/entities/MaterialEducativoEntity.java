package com.devs.demoCours.domain.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity(name = "material_educativo")
public class MaterialEducativoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_material_educativo")
    private Integer idMaterialEducativo;
    @Column(name = "description",nullable = false)
    private String Description;
    private String tipo;
    private String enlace;
    /*
    Relación con la Entidad Session
     */
    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name = "session_id")
    private SessionEntity session;
}
