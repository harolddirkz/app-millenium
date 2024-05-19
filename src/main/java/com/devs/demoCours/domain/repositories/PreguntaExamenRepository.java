package com.devs.demoCours.domain.repositories;

import com.devs.demoCours.domain.entities.PreguntaExamenEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PreguntaExamenRepository extends JpaRepository<PreguntaExamenEntity, Long> {
    @Query("select pe from pregunta_examen pe where pe.idPreguntaExamen=:id")
    Optional<PreguntaExamenEntity> buscarPorId(@Param("id") Long id);

}
