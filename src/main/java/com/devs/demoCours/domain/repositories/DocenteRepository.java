package com.devs.demoCours.domain.repositories;

import com.devs.demoCours.domain.entities.DocenteEntity;
import com.devs.demoCours.domain.entities.RoleEntity;
import com.devs.demoCours.utils.Role;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    @Query("Select d from docente d where (d.name LIKE %:nombre% or d.lastName LIKE %:nombre%) and d.activo=true order by d.fechaInscription limit 5")
    List<DocenteEntity> buscarPorNombre(@Param("nombre") String nombre);

    @Query("select d from docente d where d.dni=:dni")
    Optional<DocenteEntity> buscarPorDni(@Param("dni") String dni);
    @Query("select d from docente d where d.idPersona=:id and d.activo= true ")
    Optional<DocenteEntity> buscarPorIdAndStatus(@Param("id") Long id);
    /*
    * método para obtener 8 registros aleatorios de la tabla docentes */
    @Query("select d from docente  d where d.activo=true and d.status = true order by Rand() limit 8 ")
    List<DocenteEntity> listarDocentesActivos();
    @Query("select d from docente  d where (d.activo=true and d.status=true) and ( upper(d.name) like %:name% or upper(d.lastName) like %:name%) " )
    Page<DocenteEntity>listDocentesPageable(@Param("name") String name, Pageable pageable);

    @Query("select d.roles from docente  d where d.idPersona=:idAdmin")
    List<RoleEntity> listarDocentesRoles(@Param("idAdmin") Long idAdmin);
    /*
    obtener docentes por id del curso
     */
   /* @Query("select d from docente d inner join session s on s.docente.idPersona=d.idPersona inner join curso c " +
            "on s.curso.idCurso=c.idCurso where c.idCurso=:idCurso")*/
   // List<DocenteEntity> listDocenteForIdCurso(@Param("idCurso") Long idCurso);
    @Query(value = "select d from docente d " +
            " join  fetch d.sessionEntityList s" +
            " join fetch s.modulo m" +
            " join fetch m.curso c " +
            "where c.idCurso=:idCurso")
    List<DocenteEntity> listDocenteForIdCurso(@Param("idCurso") Long idCurso);

}
