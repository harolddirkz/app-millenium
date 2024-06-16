package com.devs.demoCours.infraestructure.services;

import com.devs.demoCours.api.models.request.ModuloCreateRequest;
import com.devs.demoCours.api.models.request.ModuloUpdateRequest;
import com.devs.demoCours.api.models.responses.response.ModuloAndSessionResponse;
import com.devs.demoCours.api.models.responses.response.ModuloResponse;
import com.devs.demoCours.api.models.responses.response.SessionResponse;
import com.devs.demoCours.domain.entities.CursoEntity;
import com.devs.demoCours.domain.entities.ModuloEntity;
import com.devs.demoCours.domain.repositories.CursoRepository;
import com.devs.demoCours.domain.repositories.ModuloRepository;
import com.devs.demoCours.infraestructure.abstractServices.ModuloService;
import com.devs.demoCours.infraestructure.abstractServices.SessionService;
import com.devs.demoCours.infraestructure.helpers.NumOrdenHelper;
import com.devs.demoCours.utils.exeptions.IdNoExist;
import com.devs.demoCours.utils.mapper.ModuloMapping;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ModuloServiceImpl implements ModuloService {
    private CursoRepository cursoRepository;
    private NumOrdenHelper numOrdenHelper;
    private ModuloMapping moduloMapping;
    private ModuloRepository moduloRepository;
    private SessionService sessionService;
    @Override
    public ModuloResponse createModulo(ModuloCreateRequest request) {
        Optional<CursoEntity> cursoEntity=cursoRepository.curso(request.getIdCurso().longValue());
        if (cursoEntity.isPresent()){

            CursoEntity curso=cursoEntity.get();
            List<ModuloEntity> list=moduloRepository.listModuloForIdCurso(Long.valueOf(request.getIdCurso()));
            Integer numOrden = numOrdenHelper.numOrdenModulo(list);
             ModuloEntity entity=ModuloEntity.builder()
                    .curso(curso)
                    .nombre(request.getNombre())
                    .detalle(request.getDetalle())
                    .numOrden(numOrden)
                    .build();
             moduloRepository.save(entity);
             return moduloMapping.entityToResponse(entity);

        }else {
             throw new IdNoExist(request.getIdCurso().toString(),"curso");
        }

    }

    @Override
    public ModuloResponse getModulo(Integer idModulo) {
        ModuloEntity moduloEntity= moduloRepository.moduloForId(idModulo).orElseThrow(()->new IdNoExist(idModulo.toString(),"Modulo"));
        return moduloMapping.entityToResponse(moduloEntity);
    }

    @Override
    public ModuloResponse updateModulo(ModuloUpdateRequest request) {
        Integer idCurso= request.getId();
        ModuloEntity moduloEntity=moduloRepository.moduloForId(idCurso).orElseThrow(()->new IdNoExist(idCurso.toString(),"curso"));
        moduloEntity.setNombre(request.getNombre());
        moduloEntity.setDetalle(request.getDetalle());
        moduloRepository.save(moduloEntity);
        return moduloMapping.entityToResponse(moduloEntity);
    }

    @Override
    public List<ModuloResponse> listModuloForIdCurso(Long idCurso) {
        List<ModuloEntity> moduloEntities=moduloRepository.listModuloForIdCurso(idCurso);
        if (moduloEntities.isEmpty()){

            return Collections.emptyList();

        }
        return moduloEntities.stream()
                .map(moduloMapping::entityToResponse)
                .toList();
    }

    @Override
    public List<ModuloAndSessionResponse> listModuloAndSessionForIdCurso(Long idCurso) {
        List<ModuloEntity> moduloEntities=moduloRepository.listModuloForIdCurso(idCurso);
        if (moduloEntities.isEmpty()){
            return Collections.emptyList();
        }
        return moduloEntities.stream()
                .map( modulo->{
                    Integer idModulo=modulo.getIdModulo();
                    List<SessionResponse> sessions=sessionService.listSessionForModulo(idModulo.longValue());
                    ModuloResponse moduloAux=moduloMapping.entityToResponse(modulo);
                    return new ModuloAndSessionResponse(moduloAux,sessions);
                }).toList();
    }

    @Override
    public Map<String, Object> deleteModulo(Integer idModulo) {
        boolean moduloExist= moduloRepository.existsById(idModulo);
        if (moduloExist){
            moduloRepository.deleteById(idModulo);
            Map<String,Object> response= new HashMap<>();
            String message="Modulo Eliminado";
            response.put("message",message);
            return response;
        }
        throw new IdNoExist(idModulo.toString(),"Modulo");
    }
}
