package com.devs.demoCours.infraestructure.abstractServices;

import com.devs.demoCours.api.models.request.CursoCreateRequest;
import com.devs.demoCours.api.models.request.CursoUpdateRequest;
import com.devs.demoCours.api.models.responses.response.CursoAndDocenteResponse;
import com.devs.demoCours.api.models.responses.response.CursoResponse;
import com.devs.demoCours.domain.entities.CursoEntity;
import com.devs.demoCours.utils.TipoCurso;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Map;

public interface CursoService {
    CursoResponse crearCurso(Long idAdmin, CursoCreateRequest request);
    CursoResponse actualizarCurso(Long idAdmin,CursoUpdateRequest request);
    List<CursoResponse> listCursos();
    Page<CursoResponse> cursoPageForName(TipoCurso tipoCurso,String name,int page,int size);
    Page<CursoAndDocenteResponse> cursoAndDocentePageForName(TipoCurso tipoCurso,String name,int page,int size);
    Map<String,Object> deleteCurso(Long idAdmin, Long idCurso);

    List<CursoAndDocenteResponse> listCursosAndDocentes(TipoCurso tipo);

    CursoResponse getCurso(Long id);


}
