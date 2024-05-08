package com.devs.demoCours.infraestructure.abstractServices;

import com.devs.demoCours.api.models.request.DocenteCreateRequest;
import com.devs.demoCours.api.models.request.DocenteUpdateRequest;
import com.devs.demoCours.api.models.responses.response.DocenteResponse;
import com.devs.demoCours.domain.entities.DocenteEntity;

import java.util.List;
import java.util.Map;

public interface DocenteService {
    DocenteResponse createDocente(DocenteCreateRequest request);

    DocenteResponse actualizarDocente(DocenteUpdateRequest request);
    List<DocenteResponse> listDocentes();
    DocenteResponse docente (Long id);

    Map<String,Object> deleteDocente(Long idAdmin, Long idDocente);
}
