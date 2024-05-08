package com.devs.demoCours.utils.mapper;
import com.devs.demoCours.api.models.responses.response.EstudianteResponse;
import com.devs.demoCours.domain.entities.EstudianteEntity;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class EstudianteMapping {
    public EstudianteResponse entityToResponse(EstudianteEntity entity){
        ModelMapper modelMapper=new ModelMapper();
        return  modelMapper.map(entity,EstudianteResponse.class);
    }
}
