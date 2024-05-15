package com.devs.demoCours.infraestructure.services;

import com.devs.demoCours.api.models.request.SessionCreateRequest;
import com.devs.demoCours.api.models.request.SessionUpdateRequest;
import com.devs.demoCours.api.models.responses.response.SessionCompleteResponse;
import com.devs.demoCours.api.models.responses.response.SessionResponse;
import com.devs.demoCours.domain.entities.*;
import com.devs.demoCours.domain.repositories.CursoRepository;
import com.devs.demoCours.domain.repositories.DocenteRepository;
import com.devs.demoCours.domain.repositories.InscriptionRepository;
import com.devs.demoCours.domain.repositories.SessionRepository;
import com.devs.demoCours.infraestructure.abstractServices.SessionService;
import com.devs.demoCours.infraestructure.helpers.RolHelper;
import com.devs.demoCours.infraestructure.helpers.SessionHelper;
import com.devs.demoCours.utils.exeptions.IdNoExist;
import com.devs.demoCours.utils.exeptions.UsuarioNoAutorizado;
import com.devs.demoCours.utils.exeptions.UsuarioNoExist;
import com.devs.demoCours.utils.mapper.SessionMapping;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@AllArgsConstructor
@Transactional
@Service
public class SessionServiceImpl implements SessionService {
    private SessionRepository sessionRepository;
    private DocenteRepository docenteRepository;
    private CursoRepository cursoRepository;
    private InscriptionRepository inscriptionRepository;
    private final RolHelper rolhelper;
    private final SessionHelper sessionHelper;
    private final SessionMapping sessionMapping;

    @Override
    public SessionResponse crearSession(Long idAdmin, SessionCreateRequest request) {
        DocenteEntity docenteAdmin = docenteRepository.buscarPorIdAndStatus(idAdmin).orElseThrow(() -> new UsuarioNoExist(idAdmin.toString()));
        DocenteEntity docenteSession = docenteRepository.buscarPorIdAndStatus(request.getIdDocente()).orElseThrow(() -> new UsuarioNoExist(request.getIdDocente().toString()));
        CursoEntity curso = cursoRepository.obtenerCurso(request.getIdCurso()).orElseThrow(() -> new IdNoExist(request.getIdCurso().toString(), "curso"));
        List<RoleEntity> roles = docenteAdmin.getRoles();

        if (rolhelper.esAdmin(roles)) {
            Integer numOrden = sessionHelper.numOrden(curso.getSesionEntityList());
            SesionEntity session = SesionEntity.builder()
                    .docente(docenteSession)
                    .curso(curso)
                    .descripcion(request.getDescripcion())
                    .material(request.getMaterial())
                    .numOrden(numOrden)
                    .status(request.isStatus())
                    .inicioSesion(request.getInicioSesion())
                    .finalSesion(request.getFinalSesion())
                    .build();
            sessionRepository.save(session);
            return sessionMapping.entityToResponse(session);

        } else {
            throw new UsuarioNoAutorizado(idAdmin.toString());
        }
    }

    @Override
    public List<SessionResponse> listSessionForCurso(Long idCurso) {
        List<SesionEntity> listSessions = sessionRepository.listSession(idCurso);
        List<SessionResponse> response = new ArrayList<>();
        for (SesionEntity session : listSessions) {
            response.add(sessionMapping.entityToResponse(session));
        }
        return response;
    }

    @Override
    public SessionCompleteResponse session(Long idSession, Long idCurso, Long idEstudiante) {
        Optional<InscripcionEntity> inscription = inscriptionRepository.buscarPorIdCursoAndIdStudent(idCurso,idEstudiante);
        if(inscription.isPresent()){
            SesionEntity session = sessionRepository.findById(idSession).orElseThrow(() -> new IdNoExist(idSession.toString(), "Sesión"));

            return sessionMapping.entityToResponseComplete(session);
        }else {
            throw new UsuarioNoAutorizado(idEstudiante.toString());
        }

    }



    @Override
    public SessionResponse editSession(Long idAdmin, SessionUpdateRequest request) {
        Long idSession = request.getId();
        SesionEntity sessionDb = sessionRepository.findById(idSession).orElseThrow(() -> new IdNoExist(idSession.toString(), "Sesión"));
        DocenteEntity docenteDb = docenteRepository.buscarPorIdAndStatus(idAdmin).orElseThrow(()->new UsuarioNoExist(idAdmin.toString()));
        List<RoleEntity> roles = docenteDb.getRoles();
        if(rolhelper.esAdmin(roles)){
            sessionDb.setDescripcion(request.getDescripcion());
            sessionDb.setMaterial(request.getMaterial());
            sessionDb.setStatus(request.isStatus());
            sessionDb.setInicioSesion(request.getInicioSesion());
            sessionDb.setFinalSesion(request.getFinalSesion());
            sessionRepository.save(sessionDb);
            return sessionMapping.entityToResponse(sessionDb);

        }else {
            throw new UsuarioNoAutorizado(idAdmin.toString());
        }
    }
}
