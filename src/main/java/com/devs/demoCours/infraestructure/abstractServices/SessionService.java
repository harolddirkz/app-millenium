package com.devs.demoCours.infraestructure.abstractServices;

import com.devs.demoCours.api.models.request.SessionCreateRequest;
import com.devs.demoCours.api.models.request.SessionUpdateRequest;
import com.devs.demoCours.api.models.responses.response.SessionCompleteResponse;
import com.devs.demoCours.api.models.responses.response.SessionResponse;

import java.util.List;

public interface SessionService {
    SessionResponse crearSession(Long idAdmin, SessionCreateRequest request);
    List<SessionResponse> listSessionForCurso(Long idCurso);
    SessionCompleteResponse session(Long idSession, Long idCurso, Long idEstudiante);
    SessionResponse editSession(Long idAdmin, SessionUpdateRequest request);

}
