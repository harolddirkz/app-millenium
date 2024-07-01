package com.devs.demoCours.domain.repositories;

import com.devs.demoCours.domain.entities.AlternativaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlternativaRepository extends JpaRepository<AlternativaEntity,Long> {
}
