package com.devs.demoCours.utils.mapper;

import com.devs.demoCours.api.models.responses.response.InscriptionResponse;
import com.devs.demoCours.domain.entities.InscriptionEntity;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class InscriptionMapping {
    public InscriptionResponse entityToResponse(InscriptionEntity entity){
        ModelMapper modelMapper=new ModelMapper();
        return  modelMapper.map(entity,InscriptionResponse.class);
    }
}
