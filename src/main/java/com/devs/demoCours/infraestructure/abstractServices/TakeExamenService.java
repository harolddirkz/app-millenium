package com.devs.demoCours.infraestructure.abstractServices;

import com.devs.demoCours.api.models.request.TakeExamenCreateRequest;
import com.devs.demoCours.api.models.request.TakeExamenUpdateRequest;
import com.devs.demoCours.api.models.responses.response.TakeExamenResponse;
import com.devs.demoCours.domain.entities.TakeExamenEntity;

public interface TakeExamenService {
    TakeExamenResponse crear(TakeExamenCreateRequest request);
    TakeExamenResponse update(TakeExamenUpdateRequest request);
}
