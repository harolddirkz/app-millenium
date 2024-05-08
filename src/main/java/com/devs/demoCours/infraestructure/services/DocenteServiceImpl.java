package com.devs.demoCours.infraestructure.services;

import com.devs.demoCours.api.models.request.DocenteCreateRequest;
import com.devs.demoCours.api.models.request.DocenteUpdateRequest;
import com.devs.demoCours.api.models.responses.response.DocenteResponse;
import com.devs.demoCours.domain.entities.DocenteEntity;
import com.devs.demoCours.domain.entities.EstudianteEntity;
import com.devs.demoCours.domain.entities.RoleEntity;
import com.devs.demoCours.domain.repositories.DocenteRepository;
import com.devs.demoCours.domain.repositories.EstudianteRepository;
import com.devs.demoCours.domain.repositories.RoleRepository;
import com.devs.demoCours.infraestructure.abstractServices.DocenteService;
import com.devs.demoCours.utils.Role;
import com.devs.demoCours.utils.exeptions.UsuarioDuplicado;
import com.devs.demoCours.utils.exeptions.UsuarioNoExist;
import com.devs.demoCours.utils.mapper.DocenteMapping;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class DocenteServiceImpl implements DocenteService {
    private EstudianteRepository estudianteRepository;
    private DocenteRepository docenteRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;
    private DocenteMapping docenteMapping;

    /*
    Creará un registro la base de datos docente con los datos que se le proporciona en el DocenteRequest.
    Para lo cual primero verificará si existe un docente con el mismo dni en la tabla docente; si es que hay un docente almacenado
    con el dni proporcionado en DocenteRequest el programa arrojará una excepción, de lo contrario continuara
    con el registro del docente. También se creará un registro en la tabla Estudiante previo a una verificación (verificará si esta existe en la tabla estudiántes).
     */
    @Override
    @Transactional
    public DocenteResponse createDocente(DocenteCreateRequest request) {
        Optional<DocenteEntity> existeDocente = docenteRepository.existeDocente(request.getDni());
        RoleEntity rolStudent = roleRepository.findByName(Role.ROLE_STUDENT).orElseThrow();
        RoleEntity rolTeach = roleRepository.findByName(Role.ROLE_TEACH).orElseThrow();
        List<RoleEntity> roles = new ArrayList<>();
        roles.add(rolStudent);
        if (existeDocente.isEmpty()) {
            Optional<EstudianteEntity> existeEstudiante = estudianteRepository.existeEstudiante(request.getDni());

            if (existeEstudiante.isEmpty()) {
                EstudianteEntity estudiante = EstudianteEntity.builder()
                        .dni(request.getDni())
                        .name(request.getName())
                        .lastName(request.getLastName())
                        .email(request.getEmail())
                        .password(passwordEncoder.encode(request.getPassword()))
                        .fechaIncripcion(LocalDate.now())
                        .status(true)
                        .roles(roles)
                        .build();
                estudianteRepository.save(estudiante);
            }
            roles.add(rolTeach);
            return  docenteMapping.entityToResponse(crearDocente(request, roles));


        } else {
            throw new UsuarioDuplicado(request.getDni());
        }
    }

    /*
    Este método Actualizara datos adicionales de un registro docente, previo a una verificación.
    Si el registro a editar no existe, se dara paso a un error; caso contrario procederá a modificar el
    registro con los datos dados en el DocenteUpdateRequest
     */
    @Override
    @Transactional
    public DocenteResponse actualizarDocente(DocenteUpdateRequest request) {
        Optional<DocenteEntity> docente = docenteRepository.findById(request.getId());
        if (docente.isPresent()) {
            DocenteEntity docenteUpdate = docente.get();
            docenteUpdate.setAvatar(request.getAvatar());
            docenteUpdate.setGenero(request.getGenero());
            docenteUpdate.setBirthDate(request.getBirthDate());
            docenteUpdate.setEspecialidad(request.getEspecialidad());
            docenteUpdate.setResenia(request.getResenia());
            docenteRepository.save(docenteUpdate);
            return docenteMapping.entityToResponse(docenteUpdate);

        } else {
            throw new UsuarioNoExist(request.getDni());
        }
    }

    /*
    Esté método listará los docentes que se encuentres activos

     */
    @Override
    public List<DocenteResponse> listDocentes() {
        List<DocenteEntity> docentes = docenteRepository.listarDocentesActivos();
        List<DocenteResponse> response = new ArrayList<>();
        for(DocenteEntity res:docentes){
            response.add(docenteMapping.entityToResponse(res));
        }
        return response;
    }


    /*
    método para crear y guardar docente
     */
    private DocenteEntity crearDocente(DocenteCreateRequest request, List<RoleEntity> listRol) {
        DocenteEntity docente = DocenteEntity.builder()
                .dni(request.getDni())
                .name(request.getName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .especialidad(request.getEspecialidad())
                .status(true)
                .activo(true)
                .roles(listRol)
                .build();
        docenteRepository.save(docente);
        return docente;
    }
}
