package com.devs.demoCours.domain.repositories;

import com.devs.demoCours.domain.entities.InscripcionEntity;
import com.devs.demoCours.domain.entities.SesionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface InscriptionRepository extends JpaRepository<InscripcionEntity, Long> {
    @Query("select i from inscripcion  i where i.curso.idCurso=:idCurso and i.estudiante.idPersona=:idStudent")
    Optional<InscripcionEntity> buscarPorIdCursoAndIdStudent(@Param("idCurso") Long idCurso, @Param("idStudent") Long idStudent);

    @Query("select i.curso.sesionEntityList from inscripcion i where i.curso.idCurso=:idCurso and i.estudiante.idPersona=:idStudent")
    List<SesionEntity> sessionForIdAndIdIncr(@Param("idCurso") Long idCurso, @Param("idStudent") Long idStudent);

    @Query("select i from inscripcion i where i.estudiante.idPersona=:idEstudiante")
    List<InscripcionEntity> inscriptionsByDniStudent(@Param("idEstudiante") Long idEstudiante);
}
