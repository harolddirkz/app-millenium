package com.devs.demoCours.domain.repositories;

import com.devs.demoCours.domain.entities.EstudianteEntity;
import com.devs.demoCours.domain.entities.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EstudianteRepository extends JpaRepository<EstudianteEntity,Long> {
    @Query("Select e from estudiante e where e.email=:email")
    Optional<EstudianteEntity> buscarPorEmail(@Param("email") String email);
    @Query("select e from estudiante e where e.dni=:dni")
    Optional<EstudianteEntity> existeEstudiante(@Param("dni") String dni);
    @Query("select e from estudiante e where e.idPersona=:id and e.status=true ")
    Optional<EstudianteEntity> EstudiantePorId(@Param("id") Long id);

   @Query("select e from estudiante e where e.status=true")
    List<EstudianteEntity> listEstudiantesActivos();


}
