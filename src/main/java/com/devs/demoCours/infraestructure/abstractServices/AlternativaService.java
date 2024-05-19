package com.devs.demoCours.infraestructure.abstractServices;

import com.devs.demoCours.api.models.request.AlternativaRequest;
import com.devs.demoCours.api.models.request.AlternativaUpdateRequest;
import com.devs.demoCours.api.models.responses.response.AlternativaResponse;

import java.util.Map;

public interface AlternativaService {
    AlternativaResponse create(AlternativaRequest request);
    AlternativaResponse update(AlternativaUpdateRequest request, Long id);
    Map<String,Object> delete(Long id);

}
