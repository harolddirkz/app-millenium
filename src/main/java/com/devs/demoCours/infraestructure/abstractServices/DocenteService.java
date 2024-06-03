package com.devs.demoCours.infraestructure.abstractServices;

import com.devs.demoCours.api.models.request.DocenteCreateRequest;
import com.devs.demoCours.api.models.request.DocenteUpdateRequest;
import com.devs.demoCours.api.models.responses.response.DocenteResponse;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Map;

public interface DocenteService {
    DocenteResponse createDocente(DocenteCreateRequest request);

    DocenteResponse actualizarDocente(DocenteUpdateRequest request);

    List<DocenteResponse> listDocentes();

    Page<DocenteResponse> ListDocentesPaginado(String name, int page, int size);

    DocenteResponse docente(Long id);

    DocenteResponse docenteByEmail(String email);

    Map<String, Object> deleteDocente(Long idAdmin, Long idDocente);

    /*
    obtener docentes según id de un curso
    * */
    List<DocenteResponse> listDocenteForIdCurso(Long idCurso);
}
