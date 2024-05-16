package com.devs.demoCours.utils.mapper;


import com.devs.demoCours.api.models.responses.response.SessionCompleteResponse;
import com.devs.demoCours.api.models.responses.response.SessionResponse;

import com.devs.demoCours.domain.entities.SessionEntity;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class SessionMapping {
    public SessionResponse entityToResponse(SessionEntity entity){
        ModelMapper modelMapper=new ModelMapper();
        return  modelMapper.map(entity,SessionResponse.class);
    }
    public SessionCompleteResponse entityToResponseComplete(SessionEntity entity){
        ModelMapper modelMapper=new ModelMapper();
        return  modelMapper.map(entity,SessionCompleteResponse.class);
    }
}
