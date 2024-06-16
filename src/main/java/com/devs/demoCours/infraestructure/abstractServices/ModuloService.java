package com.devs.demoCours.infraestructure.abstractServices;

import com.devs.demoCours.api.models.request.ModuloCreateRequest;
import com.devs.demoCours.api.models.request.ModuloUpdateRequest;
import com.devs.demoCours.api.models.responses.response.ModuloAndSessionResponse;
import com.devs.demoCours.api.models.responses.response.ModuloResponse;

import java.util.List;
import java.util.Map;

public interface ModuloService {
ModuloResponse createModulo(ModuloCreateRequest request);
ModuloResponse getModulo(Integer idModulo);
ModuloResponse updateModulo(ModuloUpdateRequest request);

List<ModuloResponse> listModuloForIdCurso(Long idCurso);

List<ModuloAndSessionResponse> listModuloAndSessionForIdCurso(Long idCurso);
Map<String,Object> deleteModulo(Integer idModulo);
}
