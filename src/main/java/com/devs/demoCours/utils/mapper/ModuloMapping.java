package com.devs.demoCours.utils.mapper;

import com.devs.demoCours.api.models.responses.response.ModuloResponse;
import com.devs.demoCours.domain.entities.ModuloEntity;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class ModuloMapping {
    public ModuloResponse entityToResponse(ModuloEntity entity){
        ModelMapper modelMapper = new ModelMapper();
        return  modelMapper.map(entity, ModuloResponse.class);
    }
}
