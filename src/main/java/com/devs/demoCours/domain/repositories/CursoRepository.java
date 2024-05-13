package com.devs.demoCours.domain.repositories;

import com.devs.demoCours.domain.entities.CursoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CursoRepository extends JpaRepository<CursoEntity,Long> {
    @Query("select c from curso c where c.activo=true ")
    List<CursoEntity> listarCursosActivos();
    @Query("select c from curso c where c.idCurso=:idCurso and c.activo=true ")
    Optional<CursoEntity> obtenerCurso(@Param("idCurso" )Long idCurso);
}
