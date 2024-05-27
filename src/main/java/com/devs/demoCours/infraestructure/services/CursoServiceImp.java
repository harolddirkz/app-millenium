package com.devs.demoCours.infraestructure.services;

import com.devs.demoCours.api.models.request.CursoCreateRequest;
import com.devs.demoCours.api.models.request.CursoUpdateRequest;
import com.devs.demoCours.api.models.responses.response.CursoResponse;
import com.devs.demoCours.domain.entities.CursoEntity;
import com.devs.demoCours.domain.entities.DocenteEntity;
import com.devs.demoCours.domain.entities.RoleEntity;
import com.devs.demoCours.domain.repositories.CursoRepository;
import com.devs.demoCours.domain.repositories.DocenteRepository;
import com.devs.demoCours.infraestructure.abstractServices.CursoService;
import com.devs.demoCours.infraestructure.helpers.RolHelper;
import com.devs.demoCours.utils.exeptions.IdNoExist;
import com.devs.demoCours.utils.exeptions.UsuarioNoAutorizado;
import com.devs.demoCours.utils.exeptions.UsuarioNoExist;
import com.devs.demoCours.utils.mapper.CursoMapping;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@AllArgsConstructor
public class CursoServiceImp implements CursoService {
    private DocenteRepository docenteRepository;
    private CursoRepository cursoRepository;
    private final RolHelper rolHelper;
    private final CursoMapping cursoMapping;

    /*
    Esté servicio se encarga de crear un registro en la tabla curso,
     para lo cual requiere Id de un administrador y el cursoCreateRequest
     */
    @Override
    public CursoResponse crearCurso(Long idAdmin, CursoCreateRequest request) {
        Optional<DocenteEntity> docenteAdmin = docenteRepository.buscarPorIdAndStatus(idAdmin);
        if (docenteAdmin.isPresent()) {
            List<RoleEntity> roles = docenteAdmin.get().getRoles();
            if (rolHelper.esAdmin(roles)) {
                CursoEntity cursoNew = CursoEntity.builder()
                        .nombre(request.getNombre())
                        .tipoCurso(request.getTipoCurso())
                        .detalle(request.getDetalle())
                        .fechaInicio(request.getFechaInicio())
                        .fechaFin(request.getFechaFin())
                        .modalidad(request.getModalidadCurso())
                        .duration(request.getDuration())
                        .activo(request.isActivo())
                        .build();
                cursoRepository.save(cursoNew);
                return cursoMapping.entityToResponse(cursoNew);
            } else {
                throw new UsuarioNoAutorizado(idAdmin.toString());
            }

        } else {
            throw new UsuarioNoExist(idAdmin.toString());
        }
    }
    /*
        Esté servicio se encarga de actualizar un registro en la tabla curso,
         para lo cual requiere Id de un administrador y el cursoUpdateRequest
         */
    @Override
    public CursoResponse actualizarCurso(Long idAdmin, CursoUpdateRequest request) {
        Optional<CursoEntity> cursoDb = cursoRepository.findById(request.getId());
        if (cursoDb.isPresent()) {
            DocenteEntity docenteAdmin = docenteRepository.buscarPorIdAndStatus(idAdmin).orElseThrow(() -> new UsuarioNoExist(idAdmin.toString()));
            List<RoleEntity> roles = docenteAdmin.getRoles();
            if (rolHelper.esAdmin(roles)) {
                CursoEntity cursoUpdate = getCursoEntity(request, cursoDb.get());
                cursoRepository.save(cursoUpdate);
                return cursoMapping.entityToResponse(cursoUpdate);

            } else {
                throw new UsuarioNoAutorizado(idAdmin.toString());
            }

        } else {
            throw new IdNoExist(request.getId().toString(), "curso");
        }

    }
/*
método para actualizar un curso requiere un CursoUpdateRequest y un CursoEntity, devuelve un CursoEntity
 */
    private static CursoEntity getCursoEntity(CursoUpdateRequest request, CursoEntity cursoDb) {
        cursoDb.setNombre(request.getNombre());
        cursoDb.setTipoCurso(request.getTipoCurso());
        cursoDb.setDetalle(request.getDetalle());
        cursoDb.setFechaInicio(request.getFechaInicio());
        cursoDb.setFechaFin(request.getFechaFin());
        cursoDb.setModalidad(request.getModalidadCurso());
        cursoDb.setActivo(request.isActivo());
        cursoDb.setDuration(request.getDuration());
        return cursoDb;
    }

    /*
    Este servicio listará los cursos que estén activos
     */
    @Override
    public List<CursoResponse> listCursos() {
        List<CursoResponse> responses=new ArrayList<>();
        for(CursoEntity res:cursoRepository.listarCursosActivos()){
            responses.add(cursoMapping.entityToResponse(res));
        }
        return responses;
    }
    /*
    Este servicio pasará el estado de un curso a False. Siempre en cuando exista el curso y Id pertenezca a un admin
     */
    @Override
    public Map<String, Object> deleteCurso(Long idAdmin, Long idCurso) {
        CursoEntity cursoBd =cursoRepository.obtenerCurso(idCurso).orElseThrow(()->new  IdNoExist(idCurso.toString(),"curso"));
        DocenteEntity docenteAdmin = docenteRepository.buscarPorIdAndStatus(idAdmin).orElseThrow(()->new UsuarioNoAutorizado(idAdmin.toString()));
        List<RoleEntity> roles = docenteAdmin.getRoles();
        if(rolHelper.esAdmin(roles)){
            Map<String,Object> response=new HashMap<>();
            cursoBd.setActivo(false);
            cursoRepository.save(cursoBd);
            response.put("message", " el curso: " + cursoBd.getNombre() + ", se a quitado");
            response.put("status", true);
            response.put("code", HttpStatus.OK.value());
            return response;
        }else{
            throw  new UsuarioNoAutorizado(idAdmin.toString());

        }
    }
}