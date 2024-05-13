package com.devs.demoCours.api.controllers;

import com.devs.demoCours.api.models.request.SessionCreateRequest;
import com.devs.demoCours.api.models.request.SessionUpdateRequest;
import com.devs.demoCours.api.models.responses.response.SessionResponse;
import com.devs.demoCours.infraestructure.abstractServices.SessionService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/session")
@AllArgsConstructor
public class SessionController {
    private final SessionService sessionService;
    @Operation(summary = "crear sesión", description = "creará/registrará una sesión y lo asignara a un curso")
    @PostMapping(value = "create")
    public ResponseEntity<SessionResponse> createSession(@Valid @RequestBody SessionCreateRequest request, @RequestParam Long idAdmin) {
        return ResponseEntity.ok(sessionService.crearSession(idAdmin, request));

    }
    @Operation(summary = "listar sesiones",description = "listará las sesiones que pertenezcan a un curso")
    @GetMapping(value = "listar")
    public ResponseEntity<List<SessionResponse>> listarSessions(@RequestParam Long idCurso){
        return ResponseEntity.ok(sessionService.listSessionForCurso(idCurso));
    }
    @Operation(summary = "obtener sesión",description = "retornará una sesión")
    @GetMapping(value = "session")
    public ResponseEntity<SessionResponse> session(@RequestParam Long idSession){
        return ResponseEntity.ok(sessionService.session(idSession));
    }
    @Operation(summary = "editar sesión",description = "editar una sesión, se requiere el id de un admin y el SessionUpdateRequest")
    @PutMapping(value = "update")
    public ResponseEntity<SessionResponse> update(@Valid @RequestBody SessionUpdateRequest request,Long idAdmin){
        return ResponseEntity.ok(sessionService.editSession(idAdmin,request));
    }

}
