package com.devs.demoCours.domain.repositories;

import com.devs.demoCours.domain.entities.RoleEntity;
import com.devs.demoCours.utils.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<RoleEntity,Long> {
    Optional<RoleEntity> findByName(Role name);

}
