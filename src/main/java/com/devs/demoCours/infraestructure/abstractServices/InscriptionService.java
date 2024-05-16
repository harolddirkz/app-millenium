package com.devs.demoCours.infraestructure.abstractServices;

import com.devs.demoCours.api.models.request.InscriptionCreateRequest;
import com.devs.demoCours.api.models.responses.response.InscriptionResponse;

import java.util.List;
import java.util.Map;

public interface InscriptionService {
    InscriptionResponse crear(InscriptionCreateRequest request, Long idAdmin);

    List<InscriptionResponse> listarInscription(Long idEstudiante);

    Map<String, Object> deleteInscription(Long idInscription, Long idAdmin);
}
