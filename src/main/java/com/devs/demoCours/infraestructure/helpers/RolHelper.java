package com.devs.demoCours.infraestructure.helpers;

import com.devs.demoCours.domain.entities.RoleEntity;
import com.devs.demoCours.utils.Role;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Transactional
@Component
@AllArgsConstructor
public class RolHelper {
    public boolean esAdmin(List<RoleEntity> roles) {
        boolean valor = false;
        for (RoleEntity rol : roles) {
            if (rol.getName().equals(Role.ROLE_ADMIN)) {
                valor = true;
                break;
            }
        }
        return valor;
    }
    public boolean esDocente(List<RoleEntity> roles) {
        boolean valor = false;
        for (RoleEntity rol : roles) {
            if (rol.getName().equals(Role.ROLE_TEACH)) {
                valor = true;
                break;
            }
        }
        return valor;
    }
}
