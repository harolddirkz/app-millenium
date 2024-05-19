package com.devs.demoCours.domain.repositories;

import com.devs.demoCours.domain.entities.AlternativaEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlternativaRepository extends CrudRepository<AlternativaEntity,Long> {
}
