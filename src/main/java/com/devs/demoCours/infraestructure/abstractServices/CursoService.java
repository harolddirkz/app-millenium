package com.devs.demoCours.infraestructure.abstractServices;

import com.devs.demoCours.api.models.request.CursoCreateRequest;
import com.devs.demoCours.api.models.request.CursoUpdateRequest;
import com.devs.demoCours.api.models.responses.response.CursoResponse;
import com.devs.demoCours.domain.entities.CursoEntity;

import java.util.List;
import java.util.Map;

public interface CursoService {
    CursoResponse crearCurso(Long idAdmin, CursoCreateRequest request);
    CursoResponse actualizarCurso(Long idAdmin,CursoUpdateRequest request);
    List<CursoResponse> listCursos();
    Map<String,Object> deleteCurso(Long idAdmin, Long idCurso);


}
