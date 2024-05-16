package com.devs.demoCours.infraestructure.services;

import com.devs.demoCours.api.models.request.TakeExamenCreateRequest;
import com.devs.demoCours.api.models.request.TakeExamenUpdateRequest;
import com.devs.demoCours.api.models.responses.response.TakeExamenResponse;
import com.devs.demoCours.domain.entities.ExamenSessionEntity;
import com.devs.demoCours.domain.entities.InscriptionEntity;
import com.devs.demoCours.domain.entities.TakeExamenEntity;
import com.devs.demoCours.domain.repositories.ExamenSessionRepository;
import com.devs.demoCours.domain.repositories.InscriptionRepository;
import com.devs.demoCours.domain.repositories.TakeExamenRepository;
import com.devs.demoCours.infraestructure.abstractServices.TakeExamenService;
import com.devs.demoCours.utils.exeptions.IdDuplicate;
import com.devs.demoCours.utils.exeptions.IdNoExist;
import com.devs.demoCours.utils.mapper.TakeExamenMapping;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@AllArgsConstructor
@Transactional
public class TakeExamenServiceImpl implements TakeExamenService {
    private TakeExamenRepository takeExamenRepository;
    private ExamenSessionRepository examenSessionRepository;
    private InscriptionRepository inscriptionRepository;
    private TakeExamenMapping takeExamenMapping;

    @Override
    public TakeExamenResponse crear(TakeExamenCreateRequest request) {
        Long idExamSession = request.getIdExamenSession();
        Long idInscription = request.getIdInscription();
        ExamenSessionEntity examenSession = examenSessionRepository.buscarPorId(idExamSession).orElseThrow(() -> new IdNoExist(idExamSession.toString(), "sesión"));
        InscriptionEntity inscription = inscriptionRepository.buscarPorId(idInscription).orElseThrow(() -> new IdNoExist(idInscription.toString(), "inscription"));
        Optional<TakeExamenEntity> takeExamenExist = takeExamenRepository.buscarPorIdEXamAndIdInscription(idExamSession, idInscription);
        if (takeExamenExist.isEmpty()) {
            TakeExamenEntity takeExamen = TakeExamenEntity.builder()
                    .punctuation(0f)
                    .intentos(0)
                    .examenSession(examenSession)
                    .inscription(inscription)
                    .build();
            takeExamenRepository.save(takeExamen);
            return takeExamenMapping.entityToResponse(takeExamen);
        } else {
            throw new IdDuplicate("Ya se tomó el examen de la sesión con id: "+ idExamSession);
        }

    }

    @Override
    public TakeExamenResponse update(TakeExamenUpdateRequest request) {
        TakeExamenEntity takeExamen = takeExamenRepository.buscarPorIdEXamAndIdInscription(request.getIdExamenSession(), request.getIdInscription()).orElseThrow(() -> new RuntimeException("examen no encontrado"));
        Integer intentos = takeExamen.getIntentos();
        takeExamen.setPunctuation(request.getPunctuation());
        takeExamen.setIntentos(intentos + 1);
        takeExamenRepository.save(takeExamen);

        return takeExamenMapping.entityToResponse(takeExamen);
    }
}
