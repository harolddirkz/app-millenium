package com.devs.demoCours.api.controllers;

import com.devs.demoCours.api.models.request.EstudianteUpdateRequest;
import com.devs.demoCours.api.models.responses.response.EstudianteResponse;
import com.devs.demoCours.infraestructure.abstractServices.EstudianteService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/estudiante")
@AllArgsConstructor
public class EstudianteController {
    private EstudianteService estudianteService;
    @PostMapping(value = "update")
    public ResponseEntity<EstudianteResponse> updateEstudiante(@RequestBody EstudianteUpdateRequest request){
        return ResponseEntity.ok(estudianteService.updateEstudiante(request));
    }
    @GetMapping(value = "list")
    public ResponseEntity<List<EstudianteResponse>> listEstudiantes(){
        return ResponseEntity.ok(estudianteService.listEstudiantes());
    }
    @GetMapping(value = "usuario")
    public ResponseEntity<EstudianteResponse> estudiante(@RequestParam Long id){
        System.out.println(id);
        return ResponseEntity.ok(estudianteService.estudiante(id));
    }
}
