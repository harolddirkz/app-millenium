package com.devs.demoCours.api.controllers;

import com.devs.demoCours.api.models.request.ExamenSessionCreateRequest;
import com.devs.demoCours.api.models.request.ExamenSessionUpdateRequest;
import com.devs.demoCours.domain.entities.ExamenSessionEntity;
import com.devs.demoCours.infraestructure.abstractServices.ExamenSessionService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("examen")
@AllArgsConstructor
public class ExamenSessionController {
    private ExamenSessionService examenSessionService;

    @PostMapping(value = "create")
    public ResponseEntity<ExamenSessionEntity> crear(@RequestBody ExamenSessionCreateRequest request, @RequestParam Long idDocente) {
        return ResponseEntity.ok(examenSessionService.crear(request, idDocente));
    }

    @GetMapping(value = "listar")
    public ResponseEntity<List<ExamenSessionEntity>> listForIdSession(@RequestParam Long idSession) {
        return ResponseEntity.ok(examenSessionService.listarPorSession(idSession));
    }
    @PutMapping(value = "update")
    public ResponseEntity<ExamenSessionEntity> update(@RequestBody ExamenSessionUpdateRequest request, @RequestParam Long idDocente) {
        return ResponseEntity.ok(examenSessionService.update(request, idDocente));
    }
}
