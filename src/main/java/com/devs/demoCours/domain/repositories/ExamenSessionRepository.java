package com.devs.demoCours.domain.repositories;

import com.devs.demoCours.domain.entities.ExamenSessionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ExamenSessionRepository extends JpaRepository<ExamenSessionEntity,Long> {
    @Query("select es from examen_session es where es.idExamenSession=:id")
    Optional<ExamenSessionEntity> buscarPorId(@Param("id") Long id);

    @Query("select es from examen_session es where es.session.idSession=:idSession")
    List<ExamenSessionEntity> buscarPoIdSession(@Param("idSession") Long idSession);
}
