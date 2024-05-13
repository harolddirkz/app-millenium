package com.devs.demoCours.utils.mapper;


import com.devs.demoCours.api.models.responses.response.SessionResponse;

import com.devs.demoCours.domain.entities.SesionEntity;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class SessionMapping {
    public SessionResponse entityToResponse(SesionEntity entity){
        ModelMapper modelMapper=new ModelMapper();
        return  modelMapper.map(entity,SessionResponse.class);
    }
}
