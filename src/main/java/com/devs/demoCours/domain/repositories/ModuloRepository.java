package com.devs.demoCours.domain.repositories;

import com.devs.demoCours.domain.entities.ModuloEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ModuloRepository extends JpaRepository<ModuloEntity,Integer> {

    @Query(value = "select m from modulo m where m.idModulo=:idModulo")
    Optional<ModuloEntity> moduloForId(@Param("idModulo") Integer idModulo);

    @Query(value = "select m from modulo m where m.curso.idCurso=:idCurso")
    List<ModuloEntity> listModuloForIdCurso(@Param("idCurso") Long idCurso);
}
