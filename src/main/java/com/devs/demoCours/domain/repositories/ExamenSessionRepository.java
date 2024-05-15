package com.devs.demoCours.domain.repositories;

import com.devs.demoCours.domain.entities.ExamenSesionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExamenSessionRepository extends JpaRepository<ExamenSesionEntity,Long> {
    @Query("select es from examen_sesion es where es.sesion.idSesion=:idSession")
    List<ExamenSesionEntity> buscarPoIdSession(@Param("idSession") Long idSession);
}
