package com.devs.demoCours.infraestructure.services;

import com.devs.demoCours.api.models.request.PreguntaExamenCreateRequest;
import com.devs.demoCours.api.models.responses.response.PreguntaExamenResponse;
import com.devs.demoCours.domain.entities.AlternativaEntity;
import com.devs.demoCours.domain.entities.ExamenSessionEntity;
import com.devs.demoCours.domain.entities.PreguntaExamenEntity;
import com.devs.demoCours.domain.repositories.AlternativaRepository;
import com.devs.demoCours.domain.repositories.ExamenSessionRepository;
import com.devs.demoCours.domain.repositories.PreguntaExamenRepository;
import com.devs.demoCours.infraestructure.abstractServices.PreguntaExamenService;
import com.devs.demoCours.utils.exeptions.IdNoExist;
import com.devs.demoCours.utils.mapper.PreguntaExamenMapping;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
public class PreguntaExamenServiceImpl implements PreguntaExamenService {
    private ExamenSessionRepository examenSessionRepository;
    private PreguntaExamenRepository preguntaExamenRepository;
    private AlternativaRepository alternativaRepository;
    private PreguntaExamenMapping preguntaExamenMapping;

    @Override
    public PreguntaExamenResponse crear(PreguntaExamenCreateRequest request) {
        Long idExamenSession = request.getIdExamenSession();
        ExamenSessionEntity examenSession = examenSessionRepository.buscarPorId(idExamenSession).orElseThrow(() -> new IdNoExist(idExamenSession.toString(), "Examen Sesión"));
        PreguntaExamenEntity preguntaExamen = PreguntaExamenEntity.builder()
                .pregunta(request.getPregunta())
                .examenSession(examenSession)
                .build();
        preguntaExamenRepository.save(preguntaExamen);
        return preguntaExamenMapping.entityToResponse(preguntaExamen);
    }

    @Override
    public PreguntaExamenResponse update(PreguntaExamenCreateRequest request, Long id) {
        PreguntaExamenEntity preguntaExamen = preguntaExamenRepository.buscarPorId(id).orElseThrow(() -> new IdNoExist(id.toString(), "preguntaExamen"));
        preguntaExamen.setPregunta(request.getPregunta());
        preguntaExamenRepository.save(preguntaExamen);
        return preguntaExamenMapping.entityToResponse(preguntaExamen);
    }

    @Override
    public Map<String, Object> delete(Long id) {
        PreguntaExamenEntity preguntaExamen = preguntaExamenRepository.buscarPorId(id).orElseThrow(() -> new IdNoExist(id.toString(), "preguntaExamen"));
        Map<String, Object> response = new HashMap<>();
        preguntaExamenRepository.deleteById(id);
        String res= "pregunta Eliminada";
        response.put("message",res);
        return response;

    }
}
