package com.devs.demoCours.utils.mapper;

import com.devs.demoCours.api.models.responses.response.AlternativaResponse;
import com.devs.demoCours.domain.entities.AlternativaEntity;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class AlternativaMapping {
    public AlternativaResponse entityToResponse(AlternativaEntity entity){
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(entity,AlternativaResponse.class);

    }
}
