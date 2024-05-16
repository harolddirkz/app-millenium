package com.devs.demoCours.infraestructure.abstractServices;

import com.devs.demoCours.api.models.request.ExamenSessionCreateRequest;
import com.devs.demoCours.api.models.request.ExamenSessionUpdateRequest;
import com.devs.demoCours.domain.entities.ExamenSessionEntity;

import java.util.List;

public interface ExamenSessionService {
    ExamenSessionEntity crear(ExamenSessionCreateRequest request, Long idDocente);
    List<ExamenSessionEntity> listarPorSession(Long idSession);
    ExamenSessionEntity update(ExamenSessionUpdateRequest request, Long idDocente);
}
