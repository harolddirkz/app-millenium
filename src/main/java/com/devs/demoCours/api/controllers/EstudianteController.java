package com.devs.demoCours.api.controllers;

import com.devs.demoCours.api.models.request.EstudianteUpdateRequest;
import com.devs.demoCours.api.models.responses.response.EstudianteResponse;
import com.devs.demoCours.infraestructure.abstractServices.EstudianteService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/estudiante")
@AllArgsConstructor
public class EstudianteController {
    private EstudianteService estudianteService;

    @Operation(summary = "actualizar estudiante", description = "actualizará un estudiante mediante los datos del EstudianteUpdateRequest")
    @PostMapping(value = "update")
    public ResponseEntity<EstudianteResponse> updateEstudiante(@Valid @RequestBody EstudianteUpdateRequest request) {
        return ResponseEntity.ok(estudianteService.updateEstudiante(request));
    }

    @Operation(summary = "listar estudiantes", description = "devolverá una lista de estudiantes")
    @GetMapping(value = "list")
    public ResponseEntity<List<EstudianteResponse>> listEstudiantes() {
        return ResponseEntity.ok(estudianteService.listEstudiantes());
    }

    @Operation(summary = "obtener estudiante por id", description = "devolverá una un estudiante")
    @GetMapping(value = "usuario")
    public ResponseEntity<EstudianteResponse> estudiante(@RequestParam Long id) {

        return ResponseEntity.ok(estudianteService.estudiante(id));
    }
    @Operation(summary = "obtener estudiante por id", description = "devolverá una un estudiante")
    @GetMapping(value = "usuario/email")
    public ResponseEntity<EstudianteResponse> estudianteByEmail(@RequestParam String email) {

        return ResponseEntity.ok(estudianteService.estudianteBYEmail(email));
    }

    @Operation(summary = "borrar estudiante", description = "eliminar estudiante(modificará el estado de un estudiante a falce)")
    @PutMapping(value = "delete")
    public ResponseEntity<Map<String, Object>> deleteEstudiante(@RequestParam Long idAdmin, @RequestParam Long idEstudiante) {

        return ResponseEntity.ok(estudianteService.deleteEstudiante(idAdmin, idEstudiante));
    }
}
