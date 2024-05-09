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
import com.devs.demoCours.utils.exeptions.UsuarioNoAutorizado;
import com.devs.demoCours.utils.exeptions.UsuarioNoExist;
import com.devs.demoCours.utils.mapper.DocenteMapping;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.*;

import static com.devs.demoCours.utils.Role.*;

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
        Optional<DocenteEntity> docente = docenteRepository.buscarPorDni(request.getDni());
        RoleEntity rolStudent = roleRepository.findByName(Role.ROLE_STUDENT).orElseThrow();
        RoleEntity rolTeach = roleRepository.findByName(Role.ROLE_TEACH).orElseThrow();
        List<RoleEntity> roles = new ArrayList<>();

        if (docente.isEmpty()) {
            Optional<EstudianteEntity> existeEstudiante = estudianteRepository.existeEstudiante(request.getDni());

            if (existeEstudiante.isEmpty()) {
                roles.add(rolStudent);
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
            return docenteMapping.entityToResponse(crearDocente(request, roles));


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
        for (DocenteEntity res : docentes) {
            response.add(docenteMapping.entityToResponse(res));
        }
        return response;
    }

    /*
    este método retornará un DocenteResponse, si hay un registro en la base de datos;
    de lo contrario retornara un UsuarioNotExist como error.
     */
    @Override
    public DocenteResponse docente(Long id) {
        Optional<DocenteEntity> docente = docenteRepository.findById(id);
        if (docente.isPresent()) {
            return docenteMapping.entityToResponse(docente.get());
        } else {
            throw new UsuarioNoExist(id.toString());
        }
    }

    /*
    Este método eliminará los roles y pasará a falce el atributo Active de un registro; cumpliendo una de las siguientes condiciones:
    El idAdmin ingresado tiene un rol de ROLE_ADMIN.
    El IdAdmin e idDocente pertenecen al mismo docente
    */
    @Override
    public Map<String,Object> deleteDocente(Long idAdmin, Long idDocente) {
        Optional<DocenteEntity> docenteBd = docenteRepository.buscarPorIdAndStatus(idDocente);
        Optional<DocenteEntity> docenteAdmin = docenteRepository.buscarPorIdAndStatus(idAdmin);
        Map<String, Object> response = new HashMap<>();
        if (docenteBd.isPresent()) {
            if (docenteAdmin.isPresent()) {
                List<RoleEntity> roles = docenteAdmin.get().getRoles();
                if (existeRol(roles) || docenteAdmin.equals(docenteBd)) {
                    List<RoleEntity> rolesDocente = docenteBd.get().getRoles();
                    DocenteEntity docenteDelete = docenteBd.get();
                    rolesDocente.removeIf(objet -> Objects.equals(objet.getName(), ROLE_TEACH));
                    rolesDocente.removeIf(objet -> Objects.equals(objet.getName(), ROLE_STUDENT));
                    docenteDelete.setRoles(rolesDocente);
                    docenteDelete.setActivo(false);
                    docenteDelete.setStatus(false);
                    docenteRepository.save(docenteDelete);
                    response.put("message", " el docente con dni: " + docenteDelete.getDni() + " se a quitado");
                    response.put("status", true);
                    response.put("code", HttpStatus.OK.value());
                } else {
                    /* response.put("message", " el Usuario con dni: " + docenteAdmin.get().getDni() + " no tiene los permisos suficientes");
                    response.put("status", false);
                    response.put("code", HttpStatus.UNAUTHORIZED.value());
                    */
                    throw new UsuarioNoAutorizado(idAdmin.toString());
                }
                return response;
            } else {
                throw new UsuarioNoExist(idAdmin.toString());
            }
        } else {
            throw new UsuarioNoExist(idDocente.toString());
        }


    }
    /*
    método para eliminar los roles y cambiar el status de un docente
     private void quitarRolesAndStatus(DocenteEntity entity){
        List<RoleEntity> rolesDocente = entity.getRoles();
        rolesDocente.removeIf(objet -> Objects.equals(objet.getName(), ROLE_TEACH));
        rolesDocente.removeIf(objet -> Objects.equals(objet.getName(), ROLE_STUDENT));
        entity.setRoles(rolesDocente);
        entity.setActivo(false);
        entity.setStatus(false);
    }*/
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
