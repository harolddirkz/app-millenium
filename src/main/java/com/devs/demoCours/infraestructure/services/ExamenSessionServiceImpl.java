package com.devs.demoCours.infraestructure.services;

import com.devs.demoCours.api.models.request.ExamenSessionCreateRequest;
import com.devs.demoCours.domain.entities.DocenteEntity;
import com.devs.demoCours.domain.entities.ExamenSesionEntity;
import com.devs.demoCours.domain.entities.RoleEntity;
import com.devs.demoCours.domain.entities.SesionEntity;
import com.devs.demoCours.domain.repositories.DocenteRepository;
import com.devs.demoCours.domain.repositories.ExamenSessionRepository;
import com.devs.demoCours.domain.repositories.SessionRepository;
import com.devs.demoCours.infraestructure.abstractServices.ExamenSessionService;
import com.devs.demoCours.infraestructure.helpers.RolHelper;
import com.devs.demoCours.utils.exeptions.IdNoExist;
import com.devs.demoCours.utils.exeptions.UsuarioNoAutorizado;
import com.devs.demoCours.utils.exeptions.UsuarioNoExist;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class ExamenSessionServiceImpl implements ExamenSessionService {
    private ExamenSessionRepository examenSessionRepository;
    private SessionRepository sessionRepository;
    private DocenteRepository docenteRepository;
    private RolHelper rolHelper;
    @Override
    public ExamenSesionEntity crear(ExamenSessionCreateRequest request, Long idDocente) {
        Long idSession =request.getIdSession();
        SesionEntity session=sessionRepository.findById(idSession).orElseThrow(()->new IdNoExist(idSession.toString(),"Session"));
        Long id= Long.valueOf(session.getDocente().getIdPersona());
        DocenteEntity docente = docenteRepository.buscarPorIdAndStatus(idDocente).orElseThrow(()->new UsuarioNoExist(idDocente.toString()));
        List<RoleEntity> roles = docente.getRoles();
        if(rolHelper.esAdmin(roles)|| (rolHelper.esDocente(roles) && idDocente.equals(id))){
            ExamenSesionEntity examenSession=ExamenSesionEntity.builder()
                    .fechaExamen(request.getFechaExamen())
                    .Detalles(request.getDetalles())
                    .sesion(session)
                    .build();
            examenSessionRepository.save(examenSession);

            return examenSession;
        }else {
            throw new UsuarioNoAutorizado(idDocente.toString());
        }


    }

    @Override
    public List<ExamenSesionEntity> listarPorSession(Long idSession) {
        Optional<SesionEntity> session=sessionRepository.findById(idSession);
        if(session.isPresent()){
            return examenSessionRepository.buscarPoIdSession(idSession);
        }else {
            throw new IdNoExist(idSession.toString(),"Sesión");
        }

    }
}
