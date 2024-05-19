package com.devs.demoCours.infraestructure.abstractServices;

import com.devs.demoCours.api.models.request.PreguntaExamenCreateRequest;
import com.devs.demoCours.api.models.responses.response.PreguntaExamenResponse;
import com.devs.demoCours.domain.entities.PreguntaExamenEntity;

import java.util.Map;

public interface PreguntaExamenService {
    PreguntaExamenResponse crear(PreguntaExamenCreateRequest request);
    PreguntaExamenResponse update(PreguntaExamenCreateRequest request,Long id);
    Map<String,Object> delete(Long id);
}
