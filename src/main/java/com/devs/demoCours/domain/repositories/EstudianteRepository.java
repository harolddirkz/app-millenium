package com.devs.demoCours.domain.repositories;

import com.devs.demoCours.domain.entities.EstudianteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EstudianteRepository extends JpaRepository<EstudianteEntity,Long> {
    @Query("Select e from estudiante e where e.email=:email")
    Optional<EstudianteEntity> buscarPorEmail(@Param("email") String email);
}
