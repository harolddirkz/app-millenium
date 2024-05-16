package com.devs.demoCours.api.controllers;

import com.devs.demoCours.api.models.request.DocenteCreateRequest;
import com.devs.demoCours.api.models.request.DocenteUpdateRequest;
import com.devs.demoCours.api.models.responses.response.DocenteResponse;
import com.devs.demoCours.infraestructure.abstractServices.DocenteService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/docente")
@AllArgsConstructor
public class DocenteController {
    private DocenteService docenteService;
    @Operation(summary = "crear docente",description = "creará/registrará un docente")
    @PostMapping(value = "create")
    public ResponseEntity<DocenteResponse>createDocente( @Valid @RequestBody DocenteCreateRequest request){
        return ResponseEntity.ok(docenteService.createDocente(request));

    }
    @Operation(summary = "actualizar docente",description = "actualizará un registro en la base de datos; según los datos proporcionados en el request")
    @PostMapping( value = "update")
    public ResponseEntity<DocenteResponse>updateDocente( @Valid @RequestBody DocenteUpdateRequest request){
        return ResponseEntity.ok(docenteService.actualizarDocente(request));

    }
    @Operation(summary = "listar docente",description = "devuelve una lista de registros de docentes")
    @GetMapping(value = "list")
    public ResponseEntity<List<DocenteResponse>> listDocentes(){
        return ResponseEntity.ok(docenteService.listDocentes());
    }
    @Operation(summary = "obtener docente",description = "devuelve un registro de un docente")
    @GetMapping(value = "docente")
    public ResponseEntity<DocenteResponse> docentes(@RequestParam Long id){
        return ResponseEntity.ok(docenteService.docente(id));
    }

    @Operation(summary = "borrar docente", description = "eliminar docente(modificará el estado de un docente a falce)")
    @PutMapping(value = "delete")
    public ResponseEntity<Map<String, Object>> deleteDocente(@RequestParam Long idAdmin, @RequestParam Long idDocente) {

        return ResponseEntity.ok(docenteService.deleteDocente(idAdmin, idDocente));
    }
}
