package com.devs.demoCours.utils.mapper;

import com.devs.demoCours.api.models.responses.response.DocenteResponse;
import com.devs.demoCours.domain.entities.DocenteEntity;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class DocenteMapping {
    /*
    este método se encarga de asignar las variables de la Entidad DocenteEntity en la entidad DocenteResponse
     */
    public DocenteResponse entityToResponse(DocenteEntity entity){
        ModelMapper modelMapper=new ModelMapper();
        return  modelMapper.map(entity,DocenteResponse.class);
    }
}
