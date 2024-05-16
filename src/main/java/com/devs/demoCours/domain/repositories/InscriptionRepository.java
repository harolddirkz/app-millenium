package com.devs.demoCours.domain.repositories;

import com.devs.demoCours.domain.entities.InscriptionEntity;
import com.devs.demoCours.domain.entities.SessionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface InscriptionRepository extends JpaRepository<InscriptionEntity, Long> {

    @Query("select i from inscription  i where i.idInscription =:id")
    Optional<InscriptionEntity> buscarPorId(@Param("id") Long id);
    @Query("select i from inscription  i where i.curso.idCurso=:idCurso and i.estudiante.idPersona=:idStudent")
    Optional<InscriptionEntity> buscarPorIdCursoAndIdStudent(@Param("idCurso") Long idCurso, @Param("idStudent") Long idStudent);

    @Query("select i.curso.sessionEntityList from inscription i where i.curso.idCurso=:idCurso and i.estudiante.idPersona=:idStudent")
    List<SessionEntity> sessionForIdAndIdIncr(@Param("idCurso") Long idCurso, @Param("idStudent") Long idStudent);

    @Query("select i from inscription i where i.estudiante.idPersona=:idEstudiante")
    List<InscriptionEntity> inscriptionsByDniStudent(@Param("idEstudiante") Long idEstudiante);
}
