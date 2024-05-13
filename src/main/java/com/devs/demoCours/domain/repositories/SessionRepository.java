package com.devs.demoCours.domain.repositories;

import com.devs.demoCours.domain.entities.SesionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SessionRepository extends JpaRepository<SesionEntity,Long> {
    @Query("select s from sesion s where s.curso.idCurso =:idCurso")
    List<SesionEntity> listSession(@Param("idCurso")Long idCurso);

}
