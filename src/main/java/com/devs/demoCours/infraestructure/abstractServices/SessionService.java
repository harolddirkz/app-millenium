package com.devs.demoCours.infraestructure.abstractServices;

import com.devs.demoCours.api.models.request.SessionCreateRequest;
import com.devs.demoCours.api.models.request.SessionUpdateRequest;
import com.devs.demoCours.api.models.responses.response.SessionCompleteResponse;
import com.devs.demoCours.api.models.responses.response.SessionResponse;

import java.util.List;
import java.util.Map;

public interface SessionService {
    SessionResponse crearSession(Long idAdmin, SessionCreateRequest request);
    List<SessionResponse> listSessionForModulo(Long idModulo);

    SessionCompleteResponse session(Long idSession, Long idModulo, Long idEstudiante);

    SessionCompleteResponse editSession(Long idAdmin, SessionUpdateRequest request);

    Map<String,Object> deleteSession(Long idDocente,Long idSession);

}
