package com.devs.demoCours.infraestructure.helpers;

import com.devs.demoCours.domain.entities.SessionEntity;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
@Transactional
@Component
@AllArgsConstructor
public class SessionHelper {
    public Integer numOrden(List<SessionEntity> sessions) {
        int maxNum = sessions.isEmpty() ? 0 : sessions.stream()
                .mapToInt(SessionEntity::getNumOrden)
                .max()
                .orElse(0);
        return maxNum + 1;
    }
}
