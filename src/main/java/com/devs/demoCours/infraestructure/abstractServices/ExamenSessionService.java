package com.devs.demoCours.infraestructure.abstractServices;

import com.devs.demoCours.api.models.request.ExamenSessionCreateRequest;
import com.devs.demoCours.domain.entities.ExamenSesionEntity;

import java.util.List;

public interface ExamenSessionService {
    ExamenSesionEntity crear(ExamenSessionCreateRequest request,Long idDocente);
    List<ExamenSesionEntity> listarPorSession(Long idSession);
}
