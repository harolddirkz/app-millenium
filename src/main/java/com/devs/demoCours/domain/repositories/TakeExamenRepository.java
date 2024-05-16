package com.devs.demoCours.domain.repositories;

import com.devs.demoCours.domain.entities.TakeExamenEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TakeExamenRepository extends JpaRepository<TakeExamenEntity,Long> {
  @Query("select te from TakeExamenEntity te where  te.examenSession.idExamenSession=:idExam and te.inscription.idInscription=:idInscription")
    Optional<TakeExamenEntity> buscarPorIdEXamAndIdInscription(@Param("idExam") Long idExam,@Param("idInscription")Long idInscription);
}
