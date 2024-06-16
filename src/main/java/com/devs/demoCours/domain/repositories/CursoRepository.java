package com.devs.demoCours.domain.repositories;

import com.devs.demoCours.domain.entities.CursoEntity;
import com.devs.demoCours.utils.TipoCurso;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface CursoRepository extends JpaRepository<CursoEntity, Long> {
    @Query("select c from curso c where c.activo=true order by Rand() limit 8 ")
    List<CursoEntity> listarCursosActivos();

    @Query("select c from curso c where c.activo=true and c.tipoCurso=:type and upper(c.nombre) like %:name%")
    Page<CursoEntity> listCursosPageForName(@Param("type") TipoCurso type, @Param("name") String name, Pageable pageable);

    @Query("select c from curso c where c.idCurso=:idCurso and c.activo=true ")
    Optional<CursoEntity> obtenerCursoActivo(@Param("idCurso") Long idCurso);

    @Query("select c from curso c where c.activo=true and c.tipoCurso=:type order by Rand() limit 5 ")
    List<CursoEntity> listarCursosForTypeCurso(@Param("type") TipoCurso type);

    @Query("select c from curso c where c.idCurso=:idCurso")
    Optional<CursoEntity> curso(@Param("idCurso") Long idCurso);

    @Query(value = "select min(s.inicioSession) from session s " +
            "join s.modulo m " +
            "join m.curso c " +
            "where c.idCurso=:idCurso")
    Optional<LocalDateTime> buscarFechaMinima(@Param("idCurso") Long idCurso);
    @Query(value = "select min(s.finalSession) from session s " +
            "join s.modulo m " +
            "join m.curso c " +
            "where c.idCurso=:idCurso")
    Optional<LocalDateTime> buscarFechaMaxima(@Param("idCurso") Long idCurso);

}
