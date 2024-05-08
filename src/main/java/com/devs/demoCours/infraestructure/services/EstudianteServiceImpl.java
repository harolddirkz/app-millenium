package com.devs.demoCours.infraestructure.services;

import com.devs.demoCours.api.models.request.EstudianteUpdateRequest;
import com.devs.demoCours.api.models.responses.response.EstudianteResponse;
import com.devs.demoCours.domain.entities.DocenteEntity;
import com.devs.demoCours.domain.entities.EstudianteEntity;
import com.devs.demoCours.domain.repositories.EstudianteRepository;
import com.devs.demoCours.infraestructure.abstractServices.EstudianteService;
import com.devs.demoCours.utils.exeptions.UsuarioNoExist;
import com.devs.demoCours.utils.mapper.EstudianteMapping;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class EstudianteServiceImpl implements EstudianteService {
    private EstudianteRepository estudianteRepository;
    private EstudianteMapping estudianteMapping;
    @Override
    public EstudianteResponse updateEstudiante(EstudianteUpdateRequest request) {
        Optional<EstudianteEntity> estudiante = estudianteRepository.findById(request.getId());
        if (estudiante.isPresent()){
            EstudianteEntity estudianteUpdate=estudiante.get();
            estudianteUpdate.setAvatar(request.getAvatar());
            estudianteUpdate.setGenero(request.getGenero());
            estudianteUpdate.setBirthDate(request.getBirthDate());
            estudianteUpdate.setResenia(request.getResenia());
            estudianteRepository.save(estudianteUpdate);
            return estudianteMapping.entityToResponse(estudianteUpdate);
        }else {
            throw new UsuarioNoExist(request.getDni());
        }
    }

    @Override
    public List<EstudianteResponse> listEstudiantes() {
        List<EstudianteEntity> estudiantes=estudianteRepository.listEstudiantesActivos();
        List<EstudianteResponse> response =new ArrayList<>();
        for(EstudianteEntity res:estudiantes){
            response.add(estudianteMapping.entityToResponse(res));
        }
        return response;
    }

    @Override
    public EstudianteResponse estudiante(Long id) {
        System.out.println("en el servicio"+ id);
        Optional<EstudianteEntity> estudiante = estudianteRepository.EstudiantePorId(id);
        if(estudiante.isPresent()){
            return estudianteMapping.entityToResponse(estudiante.get());
        }else{
            throw new UsuarioNoExist(id.toString());
        }
    }


}
