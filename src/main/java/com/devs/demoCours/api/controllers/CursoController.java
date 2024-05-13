package com.devs.demoCours.api.controllers;

import com.devs.demoCours.api.models.request.CursoCreateRequest;
import com.devs.demoCours.api.models.request.CursoUpdateRequest;
import com.devs.demoCours.api.models.responses.response.CursoResponse;
import com.devs.demoCours.infraestructure.abstractServices.CursoService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/curso")
@AllArgsConstructor
public class CursoController {
    private final CursoService cursoService;

    @Operation(summary = "crear curso", description = "creará/registrará un curso")
    @PostMapping(value = "create")
    public ResponseEntity<CursoResponse> createCurso(@Valid @RequestBody CursoCreateRequest request,@RequestParam Long idAdmin) {
        return ResponseEntity.ok(cursoService.crearCurso(idAdmin, request));

    }

    @Operation(summary = "actualizar docente", description = "actualizará un curso ")
    @PostMapping(value = "update")
    public ResponseEntity<CursoResponse> updateCurso(@Valid @RequestBody CursoUpdateRequest request,@RequestParam Long idAdmin) {
        return ResponseEntity.ok(cursoService.actualizarCurso(idAdmin, request));

    }

    @Operation(summary = "listar docente", description = "listará los cursos activos que existen ")
    @GetMapping(value = "list")
    public ResponseEntity<List<CursoResponse>> listCursos() {
        return ResponseEntity.ok(cursoService.listCursos());
    }

    @Operation(summary = "borrar curso", description = "eliminar curso(modificará el estado de un curso a falce)")
    @PutMapping(value = "delete")
    public ResponseEntity<Map<String, Object>> deleteCurso(@RequestParam Long idAdmin, @RequestParam Long idCurso) {

        return ResponseEntity.ok(cursoService.deleteCurso(idAdmin, idCurso));
    }
}

