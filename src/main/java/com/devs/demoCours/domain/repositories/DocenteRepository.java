package com.devs.demoCours.domain.repositories;

import com.devs.demoCours.domain.entities.DocenteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface DocenteRepository extends JpaRepository<DocenteEntity,Long> {
    @Query("Select d from docente d where d.email=:email and d.activo=true ")
    Optional<DocenteEntity> buscarPorEmail(@Param("email") String email);

    @Query("select d from docente d where d.dni=:dni")
    Optional<DocenteEntity> existeDocente(@Param("dni") String dni);

    @Query("select d from docente  d where d.activo=true and d.status = true")
    List<DocenteEntity> listarDocentesActivos();
}
