package com.devs.demoCours.utils.mapper;

import com.devs.demoCours.api.models.responses.response.InscriptionResponse;
import com.devs.demoCours.api.models.responses.response.PreguntaExamenResponse;
import com.devs.demoCours.domain.entities.InscriptionEntity;
import com.devs.demoCours.domain.entities.PreguntaExamenEntity;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class PreguntaExamenMapping {
    public PreguntaExamenResponse entityToResponse(PreguntaExamenEntity entity){
        ModelMapper modelMapper=new ModelMapper();
        return  modelMapper.map(entity,PreguntaExamenResponse.class);
    }
}
