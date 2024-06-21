package com.devs.demoCours.infraestructure.services;

import com.devs.demoCours.api.models.request.SessionCreateRequest;
import com.devs.demoCours.api.models.request.SessionUpdateRequest;
import com.devs.demoCours.api.models.responses.response.SessionCompleteResponse;
import com.devs.demoCours.api.models.responses.response.SessionResponse;
import com.devs.demoCours.domain.entities.*;
import com.devs.demoCours.domain.repositories.*;
import com.devs.demoCours.infraestructure.abstractServices.SessionService;
import com.devs.demoCours.infraestructure.helpers.CursoHelper;
import com.devs.demoCours.infraestructure.helpers.DeleteEntityHelp;
import com.devs.demoCours.infraestructure.helpers.NumOrdenHelper;
import com.devs.demoCours.infraestructure.helpers.RolHelper;
import com.devs.demoCours.utils.exeptions.IdNoExist;
import com.devs.demoCours.utils.exeptions.UsuarioNoAutorizado;
import com.devs.demoCours.utils.exeptions.UsuarioNoExist;
import com.devs.demoCours.utils.mapper.SessionMapping;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;


@AllArgsConstructor
@Transactional
@Service
public class SessionServiceImpl implements SessionService {
    private SessionRepository sessionRepository;
    private DocenteRepository docenteRepository;
    private InscriptionRepository inscriptionRepository;
    private final RolHelper rolhelper;
    private final CursoHelper cursoHelper;
    private final NumOrdenHelper numOrdenHelper;
    private final SessionMapping sessionMapping;
    private final DeleteEntityHelp deleteEntityHelp;
    private ModuloRepository moduloRepository;

    @Transactional
    @Override
    public SessionResponse crearSession(Long idAdmin, SessionCreateRequest request) {
        DocenteEntity docenteAdmin = docenteRepository.buscarPorIdAndStatus(idAdmin).orElseThrow(() -> new UsuarioNoExist(idAdmin.toString()));
        DocenteEntity docenteSession = docenteRepository.buscarPorIdAndStatus(request.getIdDocente()).orElseThrow(() -> new UsuarioNoExist(request.getIdDocente().toString()));

        ModuloEntity modulo = moduloRepository.moduloForId(request.getIdModulo()).orElseThrow(() -> new IdNoExist(request.getIdModulo().toString(), "Modulo"));
        CursoEntity curso = modulo.getCurso();
        List<RoleEntity> roles = docenteAdmin.getRoles();

        if (rolhelper.esAdmin(roles)) {
            Integer numOrden = numOrdenHelper.numOrdenSession(modulo.getSessionEntities());
            long duration = Duration.between(request.getInicioSession(), request.getFinalSession()).toHours();

            SessionEntity session = SessionEntity.builder()
                    .docente(docenteSession)
                    .modulo(modulo)
                    .duration(duration)
                    .description(request.getDescription())
                    .material(request.getMaterial())
                    .numOrden(numOrden)
                    .status(request.isStatus())
                    .inicioSession(request.getInicioSession())
                    .finalSession(request.getFinalSession())
                    .build();
            sessionRepository.save(session);
            cursoHelper.actualizarDurationCurso(curso, duration);
            cursoHelper.actualizarFechaInicio(curso);
            cursoHelper.actualizarFechaFinal(curso);
            return sessionMapping.entityToResponse(session);

        } else {
            throw new UsuarioNoAutorizado(idAdmin.toString());
        }
    }

    @Override
    public List<SessionResponse> listSessionForModulo(Long idModulo) {
        List<SessionEntity> listSessions = sessionRepository.listSession(idModulo);
        List<SessionResponse> response = new ArrayList<>();
        for (SessionEntity session : listSessions) {
            response.add(sessionMapping.entityToResponse(session));
        }
        return response;
    }

    @Override
    public List<SessionCompleteResponse> listSessionCompleteForModulo(Long idModulo) {
        List<SessionEntity> listSessions = sessionRepository.listSession(idModulo);
        List<SessionCompleteResponse> response = new ArrayList<>();
        for (SessionEntity session : listSessions) {
            response.add(sessionMapping.entityToResponseComplete(session));
        }
        return response;
    }


    @Override

    public SessionCompleteResponse session(Long idSession, Long idCurso, Long idEstudiante) {
        Optional<InscriptionEntity> inscription = inscriptionRepository.buscarPorIdCursoAndIdStudent(idCurso, idEstudiante);
        if (inscription.isPresent()) {
            SessionEntity session = sessionRepository.findById(idSession).orElseThrow(() -> new IdNoExist(idSession.toString(), "Sesión"));

            return sessionMapping.entityToResponseComplete(session);
        } else {
            throw new UsuarioNoAutorizado(idEstudiante.toString());
        }

    }


    @Transactional
    @Override
    public SessionCompleteResponse editSession(Long idAdmin, SessionUpdateRequest request) {
        Long idSession = request.getId();
        SessionEntity sessionDb = sessionRepository.findById(idSession).orElseThrow(() -> new IdNoExist(idSession.toString(), "Sesión"));
        DocenteEntity docenteDb = docenteRepository.buscarPorIdAndStatus(idAdmin).orElseThrow(() -> new UsuarioNoExist(idAdmin.toString()));
        DocenteEntity docente = docenteRepository.buscarPorIdAndStatus(request.getIdDocente()).orElseThrow(() -> new UsuarioNoExist(request.getIdDocente().toString()));
        List<RoleEntity> roles = docenteDb.getRoles();
        if (rolhelper.esAdmin(roles)) {
            long oldDuration = sessionDb.getDuration();
            long newDuration = Duration.between(request.getInicioSession(), request.getFinalSession()).toHours();
            long durationDifference = newDuration - oldDuration;
            sessionDb.setDescription(request.getDescription());
            sessionDb.setMaterial(request.getMaterial());
            sessionDb.setDuration(newDuration);
            sessionDb.setStatus(request.isStatus());
            sessionDb.setDocente(docente);
            sessionDb.setInicioSession(request.getInicioSession());
            sessionDb.setFinalSession(request.getFinalSession());
            sessionRepository.save(sessionDb);
            CursoEntity curso = sessionDb.getModulo().getCurso();
            cursoHelper.actualizarDurationCurso(curso, durationDifference);
            cursoHelper.actualizarFechaInicio(curso);
            cursoHelper.actualizarFechaFinal(curso);

            return sessionMapping.entityToResponseComplete(sessionDb);

        } else {
            throw new UsuarioNoAutorizado(idAdmin.toString());
        }
    }

    @Transactional
    @Override
    public Map<String, Object> deleteSession(Long idDocente, Long idSession) {
        DocenteEntity docente = docenteRepository.buscarPorIdAndStatus(idDocente).orElseThrow(() -> new UsuarioNoExist("docente"));
        SessionEntity session = sessionRepository.findById(idSession).orElseThrow(() -> new IdNoExist(idSession.toString(), "Session"));
        CursoEntity curso = session.getModulo().getCurso();
        DocenteEntity docenteAux = session.getDocente();
        List<RoleEntity> roles = docente.getRoles();
        if (rolhelper.esAdmin(roles) || docenteAux.equals(docente)) {
            Map<String, Object> response = deleteEntityHelp.deleteEntity(sessionRepository, idSession, "Session");
            long duration = session.getDuration();
            cursoHelper.actualizarDurationCurso(curso, -duration);
            cursoHelper.actualizarFechaInicio(curso);
            cursoHelper.actualizarFechaFinal(curso);
            return response;
        } else {
            throw new UsuarioNoAutorizado(idDocente.toString());

        }
    }



}


