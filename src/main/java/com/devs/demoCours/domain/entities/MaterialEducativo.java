package com.devs.demoCours.domain.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity(name = "material_educativo")
public class MaterialEducativo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_material_educativo")
    private Integer idMaterialEducativo;
    @Column(name = "descripcion",nullable = false)
    private String Descripcion;
    private String tipo;
    private String enlace;
}
