package com.devs.demoCours.api.controllers;

import com.devs.demoCours.api.models.request.SessionCreateRequest;
import com.devs.demoCours.api.models.request.SessionUpdateRequest;
import com.devs.demoCours.api.models.responses.response.SessionCompleteResponse;
import com.devs.demoCours.api.models.responses.response.SessionResponse;
import com.devs.demoCours.infraestructure.abstractServices.SessionService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/session")
@AllArgsConstructor
public class SessionController {
    private final SessionService sessionService;
    @Operation(summary = "crear sesión", description = "creará/registrará una sesión y lo asignara a un modulo")
    @PostMapping(value = "create")
    public ResponseEntity<SessionResponse> createSession(@Valid @RequestBody SessionCreateRequest request, @RequestParam Long idAdmin) {
        return ResponseEntity.ok(sessionService.crearSession(idAdmin, request));

    }
    @Operation(summary = "listar sesiones",description = "listará las sesiones que pertenezcan a un modulo")
    @GetMapping(value = "listar")
    public ResponseEntity<List<SessionResponse>> listarSessions(@RequestParam Long idModulo){
        return ResponseEntity.ok(sessionService.listSessionForModulo(idModulo));
    }
   @Operation(summary = "obtener sesión",description = "retornará una sesión con toda su información incluido el material")
    @GetMapping(value = "complete/session")
    public ResponseEntity<SessionCompleteResponse> session(@RequestParam Long idSession, @RequestParam Long idCurso, @RequestParam Long idEstudiante){
        return ResponseEntity.ok(sessionService.session(idSession,idCurso,idEstudiante));
    }
    @Operation(summary = "editar sesión",description = "editar una sesión, se requiere el id de un admin y el SessionUpdateRequest")
    @PutMapping(value = "update")
    public ResponseEntity<SessionCompleteResponse> update(@Valid @RequestBody SessionUpdateRequest request,@RequestParam Long idAdmin){
        return ResponseEntity.ok(sessionService.editSession(idAdmin,request));
    }
    @Operation(summary = "eliminar sesión",description = "elimina una session, se requiere el id de un admin y el SessionUpdateRequest")
    @DeleteMapping(value = "delete/{id}")
    public ResponseEntity<Map<String,Object>> deleteSession(@PathVariable Long id,@RequestParam Long idDocente){
        return ResponseEntity.ok(sessionService.deleteSession(idDocente,id));
    }

}
