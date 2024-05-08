package com.devs.demoCours.Auth.Auth;

import com.devs.demoCours.Auth.Jwt.JwtService;
import com.devs.demoCours.domain.entities.DocenteEntity;
import com.devs.demoCours.domain.entities.EstudianteEntity;
import com.devs.demoCours.domain.entities.RoleEntity;
import com.devs.demoCours.domain.repositories.DocenteRepository;
import com.devs.demoCours.domain.repositories.EstudianteRepository;
import com.devs.demoCours.domain.repositories.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static com.devs.demoCours.utils.Role.ROLE_STUDENT;
import static com.devs.demoCours.utils.Role.ROLE_TEACH;

@Service
@RequiredArgsConstructor
@Transactional
public class AuthService {
    private final EstudianteRepository estudianteRepository;
    private final DocenteRepository docenteRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final RoleRepository roleRepository;

    public AuthResponse login(LoginRequest request) {
        Optional<DocenteEntity> docente =docenteRepository.buscarPorEmail(request.correo);
        UserDetails userDetails =null;
        if (docente.isPresent()){
            userDetails = docente.orElseThrow();
        }else{
            userDetails=estudianteRepository.buscarPorEmail(request.getCorreo()).orElseThrow(()->new UsernameNotFoundException("no existe usuario"));
        }

        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getCorreo(), request.getPassword()));
        String token=jwtService.getToken(userDetails);
        return AuthResponse.builder()
                .token(token)
                .build();

    }
    public AuthResponse registerDocente(RegisterRequest request) {
        RoleEntity rol = roleRepository.findByName(ROLE_TEACH).orElseThrow();
        List<RoleEntity> roles=new ArrayList<>();
        roles.add(rol);
        DocenteEntity docente =DocenteEntity.builder()
                .email(request.getEmail())
                .dni(request.getDni())
                .name(request.getName())
                .lastName(request.getLastname())
                .password(passwordEncoder.encode(request.getPassword()))
                .status(true)
                .activo(true)
                .especialidad("derecho Penal")
                .roles(roles)
                .build();
        docenteRepository.save(docente);

        return AuthResponse.builder()
                .token(jwtService.getToken(docente))
                .build();

    }

    public AuthResponse registerEstudiante(RegisterRequest request) {
        RoleEntity rol = roleRepository.findByName(ROLE_STUDENT).orElseThrow();
        List<RoleEntity> roles=new ArrayList<>();
        roles.add(rol);
        EstudianteEntity estudiante = EstudianteEntity.builder()
                .email(request.getEmail())
                .dni(request.getDni())
                .name(request.getName())
                .lastName(request.getLastname())
                .password(passwordEncoder.encode(request.getPassword()))
                .status(true)
                .fechaIncripcion(LocalDate.now())
                .roles(roles)
                .build();
        estudianteRepository.save(estudiante);

        return AuthResponse.builder()
                .token(jwtService.getToken(estudiante))
                .build();

    }


}
