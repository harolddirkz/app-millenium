package com.devs.demoCours.infraestructure.abstractServices;

import com.devs.demoCours.api.models.request.EstudianteUpdateRequest;
import com.devs.demoCours.api.models.responses.response.EstudianteResponse;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface EstudianteService {
    EstudianteResponse updateEstudiante(EstudianteUpdateRequest request);
    List<EstudianteResponse> listEstudiantes();
    EstudianteResponse estudiante(Long id);
}
