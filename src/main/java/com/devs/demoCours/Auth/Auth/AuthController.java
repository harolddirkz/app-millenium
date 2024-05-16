package com.devs.demoCours.Auth.Auth;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping(value = "login")
    public ResponseEntity<Map<String,Object>> login(@RequestBody LoginRequest request)
    {
        return ResponseEntity.ok(authService.login(request));
    }

    @PostMapping(value = "register/estudiante")
    public ResponseEntity<Map<String,Object>> register(@Valid @RequestBody RegisterRequest request)
    {
        return ResponseEntity.ok(authService.registerEstudiante(request));
    }
    /*
    @PostMapping(value = "register/docente")
    public ResponseEntity<AuthResponse> registerDocente(@RequestBody RegisterRequest request){
        return  ResponseEntity.ok(authService.registerDocente(request));
    }*/
}
