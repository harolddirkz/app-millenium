package com.devs.demoCours.api.controllers;

import com.devs.demoCours.api.models.request.PreguntaExamenCreateRequest;
import com.devs.demoCours.api.models.responses.response.PreguntaExamenResponse;
import com.devs.demoCours.domain.entities.PreguntaExamenEntity;
import com.devs.demoCours.infraestructure.abstractServices.PreguntaExamenService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/preguntaExamen")
@AllArgsConstructor
public class PreguntaExamenController {
    private PreguntaExamenService preguntaExamenService;

    @Operation(summary = "crear pregunta examen ", description = "creará/registrará una pregunta para un examen específico")
    @PostMapping(value = "create")
    public ResponseEntity<PreguntaExamenResponse> create(@Valid @RequestBody PreguntaExamenCreateRequest request) {
        return ResponseEntity.ok(preguntaExamenService.crear(request));
    }

    @Operation(summary = "actualizar pregunta examen", description = "actualizar pregunta , esto es para actualizar la pregunta")
    @PutMapping(value = "update")
    public ResponseEntity<PreguntaExamenResponse> update(@Valid @RequestBody PreguntaExamenCreateRequest request, @RequestParam Long id) {
        return ResponseEntity.ok(preguntaExamenService.update(request, id));
    }

    @Operation(summary = "eliminar pregunta examen", description = "eliminará una pregunta junto a toda sus alternativas")
    @DeleteMapping(value = "delete")
    public ResponseEntity<Map<String, Object>> delete(@RequestParam Long id) {
        return  ResponseEntity.ok(preguntaExamenService.delete(id));
    }
}
