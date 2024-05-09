package com.devs.demoCours.infraestructure.services;

import com.devs.demoCours.api.models.request.EstudianteUpdateRequest;
import com.devs.demoCours.api.models.responses.response.EstudianteResponse;
import com.devs.demoCours.domain.entities.DocenteEntity;
import com.devs.demoCours.domain.entities.EstudianteEntity;
import com.devs.demoCours.domain.entities.RoleEntity;
import com.devs.demoCours.domain.repositories.DocenteRepository;
import com.devs.demoCours.domain.repositories.EstudianteRepository;
import com.devs.demoCours.infraestructure.abstractServices.EstudianteService;
import com.devs.demoCours.utils.Role;
import com.devs.demoCours.utils.exeptions.UsuarioNoAutorizado;
import com.devs.demoCours.utils.exeptions.UsuarioNoExist;
import com.devs.demoCours.utils.mapper.EstudianteMapping;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.*;

import static com.devs.demoCours.utils.Role.*;

@Service
@AllArgsConstructor
public class EstudianteServiceImpl implements EstudianteService {
    private EstudianteRepository estudianteRepository;
    private DocenteRepository docenteRepository;
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

    @Override
    public Map<String, Object> deleteEstudiante(Long idAdmin, Long idEstudiante) {
        Optional<EstudianteEntity> estudianteDb = estudianteRepository.EstudiantePorId(idEstudiante);
        Optional<DocenteEntity> docenteAdmin = docenteRepository.buscarPorIdAndStatus(idAdmin);
        Optional<EstudianteEntity> estudianteAux = estudianteRepository.EstudiantePorId(idAdmin);
        Map<String, Object> response = new HashMap<>();
        if (estudianteDb.isPresent()) {
            if (docenteAdmin.isPresent()) {
                List<RoleEntity> roles = docenteAdmin.get().getRoles();
                if (existeRol(roles) || estudianteAux.equals(estudianteDb)) {
                    List<RoleEntity> rolesEstudiante = estudianteDb.get().getRoles();
                    EstudianteEntity estudianteDelete = estudianteDb.get();
                    rolesEstudiante.removeIf(objet -> Objects.equals(objet.getName(), ROLE_STUDENT));
                    estudianteDelete.setRoles(rolesEstudiante);
                    estudianteDelete.setStatus(false);
                    estudianteRepository.save(estudianteDelete);
                    response.put("message", " el estudiante con dni: " + estudianteDelete.getDni() + " se a quitado");
                    response.put("status", true);
                    response.put("code", HttpStatus.OK.value());
                } else {
                    throw new UsuarioNoAutorizado(idAdmin.toString());
                }
                return response;
            } else {
                throw new UsuarioNoExist(idAdmin.toString());
            }
        } else {
            throw new UsuarioNoExist(idEstudiante.toString());
        }
    }
/*
    Método para verificar si existe un rol dentro de una lista
     */

    private boolean existeRol(List<RoleEntity> roles) {
        boolean valor = false;
        for (RoleEntity rol : roles) {
            valor = rol.getName().equals(Role.ROLE_ADMIN);
        }
        return valor;

    }
}
