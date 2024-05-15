package com.devs.demoCours.api.controllers;

import com.devs.demoCours.api.models.request.InscriptionCreateRequest;
import com.devs.demoCours.api.models.responses.response.InscriptionResponse;
import com.devs.demoCours.domain.entities.InscripcionEntity;
import com.devs.demoCours.infraestructure.abstractServices.InscriptionService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RequestMapping("/inscription")
@RestController
public class InscriptionController {
    private final InscriptionService inscriptionService;
    @PostMapping(value = "create")
    public ResponseEntity<InscriptionResponse> crear(@RequestBody InscriptionCreateRequest request, @RequestParam Long idAdmin){
        return  ResponseEntity.ok(inscriptionService.crear(request, idAdmin));
    }
    @GetMapping(value="list")
    public ResponseEntity<List<InscriptionResponse>> listForIdEstudiante(@RequestParam Long idEstudiante){
        return ResponseEntity.ok(inscriptionService.listarInscription(idEstudiante));
    }
}
