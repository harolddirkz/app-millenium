package com.devs.demoCours.utils.mapper;

import com.devs.demoCours.api.models.responses.response.TakeExamenResponse;
import com.devs.demoCours.domain.entities.TakeExamenEntity;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class TakeExamenMapping {
    public TakeExamenResponse entityToResponse(TakeExamenEntity entity){
        ModelMapper modelMapper=new ModelMapper();
        return  modelMapper.map(entity,TakeExamenResponse.class);
    }
}
