package com.devs.demoCours.infraestructure.helpers;

import com.devs.demoCours.domain.entities.SesionEntity;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
@Transactional
@Component
@AllArgsConstructor
public class SessionHelper {
    public Integer numOrden(List<SesionEntity> sessions) {
        int maxNum = sessions.isEmpty() ? 0 : sessions.stream()
                .mapToInt(SesionEntity::getNumOrden)
                .max()
                .orElse(0);
        return maxNum + 1;
    }
}
