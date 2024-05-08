package com.devs.demoCours.api.controllers;

import com.devs.demoCours.api.models.request.DocenteCreateRequest;
import com.devs.demoCours.api.models.request.DocenteUpdateRequest;
import com.devs.demoCours.api.models.responses.response.DocenteResponse;
import com.devs.demoCours.domain.entities.DocenteEntity;
import com.devs.demoCours.infraestructure.abstractServices.DocenteService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/docente")
@AllArgsConstructor
public class DocenteController {
    private DocenteService docenteService;
    @PostMapping(value = "create")
    public ResponseEntity<DocenteResponse>createDocente(@RequestBody DocenteCreateRequest request){
        return ResponseEntity.ok(docenteService.createDocente(request));

    }

    @PostMapping(value = "update")
    public ResponseEntity<DocenteResponse>updateDocente(@RequestBody DocenteUpdateRequest request){
        return ResponseEntity.ok(docenteService.actualizarDocente(request));

    }
    @GetMapping(value = "list")
    public ResponseEntity<List<DocenteResponse>> listDocentes(){
        return ResponseEntity.ok(docenteService.listDocentes());
    }
}
