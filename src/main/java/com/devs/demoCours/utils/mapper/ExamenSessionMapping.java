package com.devs.demoCours.utils.mapper;

import com.devs.demoCours.api.models.responses.response.ExamenSessionResponse;
import com.devs.demoCours.domain.entities.ExamenSessionEntity;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class ExamenSessionMapping {
    public ExamenSessionResponse entityToResponse(ExamenSessionEntity entity) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(entity, ExamenSessionResponse.class);
    }
}
