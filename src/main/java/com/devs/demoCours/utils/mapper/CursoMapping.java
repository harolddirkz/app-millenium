package com.devs.demoCours.utils.mapper;

import com.devs.demoCours.api.models.responses.response.CursoResponse;
import com.devs.demoCours.domain.entities.CursoEntity;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class CursoMapping {
    /*
        este método se encarga de asignar las variables de la Entidad CursoEntity en la entidad cursoResponse
         */
    public CursoResponse entityToResponse(CursoEntity entity){
        ModelMapper modelMapper=new ModelMapper();
        return  modelMapper.map(entity,CursoResponse.class);
    }
}
