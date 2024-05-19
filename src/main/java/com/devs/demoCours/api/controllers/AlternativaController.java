package com.devs.demoCours.api.controllers;

import com.devs.demoCours.api.models.request.AlternativaRequest;
import com.devs.demoCours.api.models.request.AlternativaUpdateRequest;
import com.devs.demoCours.api.models.request.PreguntaExamenCreateRequest;
import com.devs.demoCours.api.models.responses.response.AlternativaResponse;
import com.devs.demoCours.api.models.responses.response.PreguntaExamenResponse;
import com.devs.demoCours.infraestructure.abstractServices.AlternativaService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/alternativa")
@AllArgsConstructor
public class AlternativaController {
    private AlternativaService alternativaService;

    @Operation(summary = "crear alternativa ", description = "creará/registrará una alternativa para una pregunta")
    @PostMapping(value = "create")
    public ResponseEntity<AlternativaResponse> create(@Valid @RequestBody AlternativaRequest request) {
        return ResponseEntity.ok(alternativaService.create(request));
    }

    @Operation(summary = "actualizar alternativa", description = "actualizar alternativa , esto es para actualizar la alternativa de una pregunta")
    @PutMapping(value = "update")
    public ResponseEntity<AlternativaResponse> update(@Valid @RequestBody AlternativaUpdateRequest request, @RequestParam Long id) {
        return ResponseEntity.ok(alternativaService.update(request, id));
    }

    @Operation(summary = "eliminar alternativa", description = "eliminará una alternativa junto a toda sus alternativas")
    @DeleteMapping(value = "delete")
    public ResponseEntity<Map<String, Object>> delete(@RequestParam Long id) {
        return  ResponseEntity.ok(alternativaService.delete(id));
    }
}
