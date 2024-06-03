package com.devs.demoCours.api.controllers;

import com.devs.demoCours.api.models.request.DocenteCreateRequest;
import com.devs.demoCours.api.models.request.DocenteUpdateRequest;
import com.devs.demoCours.api.models.responses.response.DocenteResponse;
import com.devs.demoCours.infraestructure.abstractServices.DocenteService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
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
    @Operation(summary = "listar docentes",description = "devuelve una lista de registros de docentes")
    @GetMapping(value = "list")
    public ResponseEntity<List<DocenteResponse>> listDocentes(){
        return ResponseEntity.ok(docenteService.listDocentes());
    }
    @Operation(summary = "listar docentes por páginas ",description = "devuelve una lista de registros de docentes por paginas")
    @GetMapping(value = "public/listPage")
    public ResponseEntity<Page<DocenteResponse>> listDocentesInPages(@RequestParam(required = false) String name,
                                                                     @RequestParam(defaultValue = "0") int page,
                                                                     @RequestParam(defaultValue = "5") int size) {
        return ResponseEntity.ok(docenteService.ListDocentesPaginado(name,page,size));
    }
    @Operation(summary = "obtener docente por id",description = "devuelve un registro de un docente")
    @GetMapping(value = "docente")
    public ResponseEntity<DocenteResponse> docente(@RequestParam Long id){
        return ResponseEntity.ok(docenteService.docente(id));
    }
    @Operation(summary = "obtener docente por id",description = "devuelve un registro de un docente")
    @GetMapping(value = "public/docenteForCurse")
    public ResponseEntity<List<DocenteResponse>> listDocenteForId(@RequestParam Long idCurso){
        return ResponseEntity.ok(docenteService.listDocenteForIdCurso(idCurso));
    }
    @Operation(summary = "obtener docente por email",description = "devuelve un registro de un docente")
    @GetMapping(value = "docente/email")
    public ResponseEntity<DocenteResponse> docenteByEmail(@RequestParam String email){
        return ResponseEntity.ok(docenteService.docenteByEmail(email));
    }

    @Operation(summary = "borrar docente por id", description = "eliminar docente(modificará el estado de un docente a falce)")
    @PutMapping(value = "delete")
    public ResponseEntity<Map<String, Object>> deleteDocente(@RequestParam Long idAdmin, @RequestParam Long idDocente) {

        return ResponseEntity.ok(docenteService.deleteDocente(idAdmin, idDocente));
    }
}
