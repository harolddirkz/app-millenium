package com.devs.demoCours.infraestructure.abstractServices;

import com.devs.demoCours.api.models.request.ExamenSessionCreateRequest;
import com.devs.demoCours.api.models.request.ExamenSessionUpdateRequest;
import com.devs.demoCours.api.models.responses.response.ExamenSessionResponse;
import com.devs.demoCours.domain.entities.ExamenSessionEntity;

import java.util.List;

public interface ExamenSessionService {
    ExamenSessionResponse crear(ExamenSessionCreateRequest request, Long idDocente);
    List<ExamenSessionResponse> listarPorSession(Long idSession);
    ExamenSessionResponse update(ExamenSessionUpdateRequest request, Long idDocente);
}
