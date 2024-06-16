package com.devs.demoCours.infraestructure.services;

import com.devs.demoCours.api.models.request.InscriptionCreateRequest;
import com.devs.demoCours.api.models.responses.response.InscriptionResponse;
import com.devs.demoCours.domain.entities.*;
import com.devs.demoCours.domain.repositories.*;
import com.devs.demoCours.infraestructure.abstractServices.InscriptionService;
import com.devs.demoCours.infraestructure.helpers.RolHelper;
import com.devs.demoCours.utils.exeptions.IdNoExist;
import com.devs.demoCours.utils.exeptions.UsuarioNoAutorizado;
import com.devs.demoCours.utils.exeptions.UsuarioNoExist;
import com.devs.demoCours.utils.mapper.InscriptionMapping;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;


@AllArgsConstructor
@Service
public class InscriptionServiceImpl implements InscriptionService {
    private  InscriptionRepository inscriptionRepository;
    private  CursoRepository cursoRepository;
    private  EstudianteRepository estudianteRepository;
    private  DocenteRepository docenteRepository;
    private  RolHelper rolHelper;
    private InscriptionMapping inscriptionMapping;

    @Override
    public InscriptionResponse crear(InscriptionCreateRequest request, Long idAdmin) {
        Long idCurso = request.getCursoId();
        Long idEstudiante = request.getEstudianteId();
        CursoEntity curso = cursoRepository.obtenerCursoActivo(idCurso).orElseThrow(() -> new IdNoExist(idCurso.toString(), "Curso"));
        EstudianteEntity estudiante = estudianteRepository.EstudiantePorId(idEstudiante).orElseThrow(() -> new UsuarioNoExist(idEstudiante.toString()));
        Optional<InscriptionEntity> inscriptionExist =inscriptionRepository.buscarPorIdCursoAndIdStudent(idCurso,idEstudiante);

        List<RoleEntity> roles = docenteRepository.listarDocentesRoles(idAdmin);
        if (inscriptionExist.isEmpty()){
            if (rolHelper.esAdmin(roles)) {
                InscriptionEntity inscription = InscriptionEntity.builder()
                        .estado(true)
                        .qualification(0f)
                        .fechaInscription(LocalDateTime.now())
                        .curso(curso)
                        .estudiante(estudiante)
                        .build();
                inscriptionRepository.save(inscription);
                return inscriptionMapping.entityToResponse(inscription);
            } else {
                throw new UsuarioNoAutorizado(idAdmin.toString());
            }
        }else {
            return inscriptionMapping.entityToResponse( inscriptionExist.get());

        }


    }

    @Override
    public List<InscriptionResponse> listarInscription(Long idEstudiante) {
        List<InscriptionEntity> inscriptions = inscriptionRepository.inscriptionsByDniStudent(idEstudiante);
        List<InscriptionResponse> response =new ArrayList<>();
        for (InscriptionEntity inscription:inscriptions){
            response.add(inscriptionMapping.entityToResponse(inscription));
        }
            return response;
    }

    @Override
    public Map<String, Object> deleteInscription(Long idInscription, Long idAdmin) {
        return null;
    }
}
