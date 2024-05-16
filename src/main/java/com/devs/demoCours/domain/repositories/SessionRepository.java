package com.devs.demoCours.domain.repositories;

import com.devs.demoCours.domain.entities.SessionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SessionRepository extends JpaRepository<SessionEntity,Long> {
    @Query("select s from session s where s.curso.idCurso =:idCurso")
    List<SessionEntity> listSession(@Param("idCurso")Long idCurso);



}
