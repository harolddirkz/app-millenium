package com.devs.demoCours.api.controllers;
import com.devs.demoCours.api.models.request.TakeExamenCreateRequest;
import com.devs.demoCours.api.models.request.TakeExamenUpdateRequest;
import com.devs.demoCours.api.models.responses.response.TakeExamenResponse;
import com.devs.demoCours.domain.entities.TakeExamenEntity;
import com.devs.demoCours.infraestructure.abstractServices.TakeExamenService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/takeExam")
@AllArgsConstructor
public class TakeExamenController {
    private TakeExamenService takeExamenService;
    @Operation(summary = "crear takeExamen ", description = "creará/registrará un take examen")
    @PostMapping(value = "create")
    public ResponseEntity<TakeExamenResponse> create(@Valid @RequestBody TakeExamenCreateRequest request) {
        return ResponseEntity.ok(takeExamenService.crear(request));
    }

    @Operation(summary = "actualizar takeExamen", description = "actualizar takeExamen, esto es para actualizar la puntuación de un examen tomado")
    @PutMapping(value = "update")
    public ResponseEntity<TakeExamenResponse> update(@Valid @RequestBody TakeExamenUpdateRequest request) {
        return ResponseEntity.ok(takeExamenService.update(request));
    }
}
