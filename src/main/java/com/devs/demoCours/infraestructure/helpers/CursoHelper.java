package com.devs.demoCours.infraestructure.helpers;

import com.devs.demoCours.domain.entities.CursoEntity;
import com.devs.demoCours.domain.repositories.CursoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

@Component
@AllArgsConstructor
public class CursoHelper {
    private CursoRepository cursoRepository;

    public void actualizarFechaInicio(CursoEntity curso) {
        Long idCurso = Long.valueOf(curso.getIdCurso());
        Optional<LocalDateTime> fecha = cursoRepository.buscarFechaMinima(idCurso);
        if (fecha.isPresent()) {
            LocalDate fechaAux = fecha.map(LocalDateTime::toLocalDate).orElseThrow();
            curso.setFechaInicio(fechaAux);
            cursoRepository.save(curso);
        }
    }
    public void actualizarFechaFinal(CursoEntity curso) {
        Long idCurso = Long.valueOf(curso.getIdCurso());
        Optional<LocalDateTime> fecha = cursoRepository.buscarFechaMaxima(idCurso);
        if (fecha.isPresent()) {
            LocalDate fechaAux = fecha.map(LocalDateTime::toLocalDate).orElseThrow();
            curso.setFechaFin(fechaAux);
            cursoRepository.save(curso);
        }
    }
    public void actualizarDurationCurso(CursoEntity curso, long durationChange) {
        long durationCurso = curso.getDuration() + durationChange;
        curso.setDuration(durationCurso);
        cursoRepository.save(curso);
    }
}
