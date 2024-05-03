package com.devs.demoCours.domain.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity(name = "alternativa")
public class Alternativa implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_alternativa")
    private Integer idAlternativa;
    @Column(name = "alternativa",nullable = false)
    private String alternativa;
    @Column(name = "valido",nullable = false)
    private boolean valido;
}
