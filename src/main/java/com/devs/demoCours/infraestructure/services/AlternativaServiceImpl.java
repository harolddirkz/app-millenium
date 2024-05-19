package com.devs.demoCours.infraestructure.services;

import com.devs.demoCours.api.models.request.AlternativaRequest;
import com.devs.demoCours.api.models.request.AlternativaUpdateRequest;
import com.devs.demoCours.api.models.responses.response.AlternativaResponse;
import com.devs.demoCours.domain.entities.AlternativaEntity;
import com.devs.demoCours.domain.entities.PreguntaExamenEntity;
import com.devs.demoCours.domain.repositories.AlternativaRepository;
import com.devs.demoCours.domain.repositories.PreguntaExamenRepository;
import com.devs.demoCours.infraestructure.abstractServices.AlternativaService;
import com.devs.demoCours.utils.exeptions.IdNoExist;
import com.devs.demoCours.utils.mapper.AlternativaMapping;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
public class AlternativaServiceImpl implements AlternativaService {
    private PreguntaExamenRepository preguntaExamenRepository;
    private AlternativaRepository alternativaRepository;
    private AlternativaMapping alternativaMapping;

    @Override
    public AlternativaResponse create(AlternativaRequest request) {
        Long idPregunta = request.getPreguntaExamen();
        PreguntaExamenEntity preguntaExamen = preguntaExamenRepository.buscarPorId(idPregunta).orElseThrow(() -> new IdNoExist(idPregunta.toString(), "PreguntaExamen"));
        List<AlternativaEntity> alternativaList = preguntaExamen.getAlternativaEntityList();
        if (alternativaList.size() < 5) {
            AlternativaEntity alternativa = AlternativaEntity.builder()
                    .alternativa(request.getAlternativa())
                    .valido(request.isValido())
                    .preguntaExamen(preguntaExamen)
                    .build();
            alternativaRepository.save(alternativa);
            return alternativaMapping.entityToResponse(alternativa);
        } else {
            throw new RuntimeException("la pregunta ya tiene 5 alternativas");
        }
    }

    @Override
    public AlternativaResponse update(AlternativaUpdateRequest request, Long id) {
        AlternativaEntity alternativa = alternativaRepository.findById(id).orElseThrow(()->new IdNoExist(id.toString(),"Alternativa"));
        alternativa.setAlternativa(request.getAlternativa());
        alternativa.setValido(request.isValido());
        alternativaRepository.save(alternativa);
        return alternativaMapping.entityToResponse(alternativa);
    }

    @Override
    public Map<String, Object> delete(Long id) {
        AlternativaEntity alternativa= alternativaRepository.findById(id).orElseThrow(() -> new IdNoExist(id.toString(), "preguntaExamen"));
        Map<String, Object> response = new HashMap<>();
        alternativaRepository.deleteById(id);
        String res= "alternativa Eliminada";
        response.put("message",res);
        return response;
    }
}
