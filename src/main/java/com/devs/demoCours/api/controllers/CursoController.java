package com.devs.demoCours.api.controllers;

import com.devs.demoCours.api.models.request.CursoCreateRequest;
import com.devs.demoCours.api.models.request.CursoUpdateRequest;
import com.devs.demoCours.api.models.responses.response.CursoAndDocenteResponse;
import com.devs.demoCours.api.models.responses.response.CursoResponse;
import com.devs.demoCours.infraestructure.abstractServices.CursoService;
import com.devs.demoCours.utils.TipoCurso;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
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
    public ResponseEntity<CursoResponse> createCurso(@Valid @RequestBody CursoCreateRequest request, @RequestParam Long idAdmin) {
        return ResponseEntity.ok(cursoService.crearCurso(idAdmin, request));

    }

    @Operation(summary = "actualizar docente", description = "actualizará un curso ")
    @PostMapping(value = "update")
    public ResponseEntity<CursoResponse> updateCurso(@Valid @RequestBody CursoUpdateRequest request, @RequestParam Long idAdmin) {
        return ResponseEntity.ok(cursoService.actualizarCurso(idAdmin, request));

    }

    @Operation(summary = "listar curso", description = "listará los cursos activos que existen ")
    @GetMapping(value = "list")
    public ResponseEntity<List<CursoResponse>> listCursos() {
        return ResponseEntity.ok(cursoService.listCursos());
    }

    @Operation(summary = "borrar curso", description = "eliminar curso(modificará el estado de un curso a falce)")
    @PutMapping(value = "delete")
    public ResponseEntity<Map<String, Object>> deleteCurso(@RequestParam Long idAdmin, @RequestParam Long idCurso) {

        return ResponseEntity.ok(cursoService.deleteCurso(idAdmin, idCurso));
    }

    @Operation(summary = "listar cursos por páginas", description = "listará los cursos activos que existen por páginas")
    @GetMapping(value = "public/listPage")
    public ResponseEntity<Page<CursoResponse>> listCursosInPages(@RequestParam(required = false) TipoCurso type,
                                                                 @RequestParam(required = false) String name,
                                                                 @RequestParam(defaultValue = "0") int page,
                                                                 @RequestParam(defaultValue = "5") int size) {
        return ResponseEntity.ok(cursoService.cursoPageForName(type, name, page, size));
    }

    @Operation(summary = "listar cursos por páginas", description = "listará los cursos activos que existen por páginas")
    @GetMapping(value = "listPage")
    public ResponseEntity<Page<CursoResponse>> listCursos(@RequestParam(required = false) TipoCurso type,
                                                          @RequestParam(required = false) String name,
                                                          @RequestParam(defaultValue = "0") int page,
                                                          @RequestParam(defaultValue = "5") int size,
                                                          @RequestParam(required = false) boolean status) {
        return ResponseEntity.ok(cursoService.cursoPageForNameAndStatus(type, name, page, size,status));
    }

    @Operation(summary = "listar cursos y docentes por páginas", description = "listará los cursos activos,estos cursos traerán consigo una lista de docentes. estará estructurado por páginas")
    @GetMapping(value = "public/listPage/cursosAndDocentes")
    public ResponseEntity<Page<CursoAndDocenteResponse>> listCursosAndDocentesInPages(@RequestParam(required = false) TipoCurso type,
                                                                                      @RequestParam(required = false) String name,
                                                                                      @RequestParam(defaultValue = "0") int page,
                                                                                      @RequestParam(defaultValue = "5") int size) {
        return ResponseEntity.ok(cursoService.cursoAndDocentePageForName(type, name, page, size));
    }

    @Operation(summary = "listar  5 cursos y docentes", description = "listará los cursos activos y estos cursos tendrán una lista de docentes")
    @GetMapping(value = "public/listCursosAndDocentes")
    public ResponseEntity<List<CursoAndDocenteResponse>> ListCursoAndDocente(@RequestParam TipoCurso curso) {
        return ResponseEntity.ok(cursoService.listCursosAndDocentes(curso));

    }

    @GetMapping(value = "public/curso/{id}")
    public ResponseEntity<CursoResponse> getCursoActivo(@PathVariable long id) {
        return ResponseEntity.ok(cursoService.getCursoActivo(id));

    }

    @GetMapping(value = "/curso/{id}")
    public ResponseEntity<CursoResponse> getCurso(@PathVariable long id) {
        return ResponseEntity.ok(cursoService.getCurso(id));

    }

}

