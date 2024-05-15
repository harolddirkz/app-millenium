package com.devs.demoCours.utils.mapper;

import com.devs.demoCours.api.models.responses.response.EstudianteResponse;
import com.devs.demoCours.api.models.responses.response.InscriptionResponse;
import com.devs.demoCours.domain.entities.EstudianteEntity;
import com.devs.demoCours.domain.entities.InscripcionEntity;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class InscriptionMapping {
    public InscriptionResponse entityToResponse(InscripcionEntity entity){
        ModelMapper modelMapper=new ModelMapper();
        return  modelMapper.map(entity,InscriptionResponse.class);
    }
}
