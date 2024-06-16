package com.devs.demoCours.api.controllers;

import com.devs.demoCours.api.models.request.ModuloCreateRequest;
import com.devs.demoCours.api.models.request.ModuloUpdateRequest;
import com.devs.demoCours.api.models.responses.response.ModuloAndSessionResponse;
import com.devs.demoCours.api.models.responses.response.ModuloResponse;
import com.devs.demoCours.infraestructure.abstractServices.ModuloService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/modulo")
@AllArgsConstructor
public class ModuloController {
    private ModuloService moduloService;

    @Operation(summary = "obtener Modulo ", description = "obtener un modulo por el id")
    @GetMapping(value = "public/modulo/{id}")
    public ResponseEntity<ModuloResponse> getModulo(@PathVariable Integer id) {
        return ResponseEntity.ok(moduloService.getModulo(id));

    }

    @Operation(summary = "obtener Lista Modulo ", description = "obtener un lista de módulos por el id del curso")
    @GetMapping(value = "public/modulo/moduloList")
    public ResponseEntity<List<ModuloResponse>> listModulo(@RequestParam Long idCurso) {
        return ResponseEntity.ok(moduloService.listModuloForIdCurso(idCurso));

    }
    @Operation(summary = "obtener Lista Modulo ", description = "obtener un lista de módulos por el id del curso")
    @GetMapping(value = "public/modulo/moduloSessionList")
    public ResponseEntity<List<ModuloAndSessionResponse>> listModuloAndSession(@RequestParam Long idCurso) {
        return ResponseEntity.ok(moduloService.listModuloAndSessionForIdCurso(idCurso));

    }

    @Operation(summary = "crear Modulo ", description = "crear un registro en modulo")
    @PostMapping(value = "create")
    public ResponseEntity<ModuloResponse> createModulo(@Valid @RequestBody ModuloCreateRequest request) {
        return ResponseEntity.ok(moduloService.createModulo(request));
    }

    @Operation(summary = "actualizar Modulo ", description = "actualizará un registro de modulo existente")
    @PutMapping(value = "update")
    public ResponseEntity<ModuloResponse> updateModulo(@RequestBody ModuloUpdateRequest request) {
        return ResponseEntity.ok(moduloService.updateModulo(request));

    }

    @Operation(summary = "eliminar Modulo ", description = "eliminar registro de modulo existente por el id")
    @DeleteMapping(value = "delete/{idModulo}")
    public ResponseEntity<Map<String, Object>> deleteModulo(@PathVariable Integer idModulo) {
        return ResponseEntity.ok(moduloService.deleteModulo(idModulo));
    }
}
