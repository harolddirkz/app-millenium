package com.devs.demoCours.domain.repositories;

import com.devs.demoCours.domain.entities.DocenteEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.Optional;

@DataJpaTest
class DocenteRepositoryTest {
    @Autowired
    DocenteRepository docenteRepository;
    @Autowired
    TestEntityManager testEntityManager;
    @BeforeEach
    void setUp() {
        DocenteEntity docente = DocenteEntity.builder()
                .name("jorge")
                .lastName("quispe Quispe")
                .dni("70274567")
                .email("algo.example@gmail.com")
                .password("password")
                .especialidad("Derecho Juridico")
                .status(true)
                .activo(true)
                .build();
        testEntityManager.persist(docente);
    }
    @DisplayName("Test para buscar por email")
    @Test
    void buscarPorEmail() {
        Optional<DocenteEntity> docente= docenteRepository.buscarPorEmail("algo.example@gmail.com");

        docente.ifPresent(entity -> assertEquals(entity.getEmail(), "algo.example@gmail.com"));
    }

    @Test
    void existeDocente() {
    }


    @Test
    void listarDocentesActivos() {

        DocenteEntity docente2 = DocenteEntity.builder()
                .name("jorge")
                .lastName("quispe Quispe")
                .dni("70274567")
                .email("algo.example@gmail.com")
                .password("password")
                .especialidad("Derecho Juridico")
                .status(true)
                .build();
    }
}