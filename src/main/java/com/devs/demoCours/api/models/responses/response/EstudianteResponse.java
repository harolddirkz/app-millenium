package com.devs.demoCours.api.models.responses.response;

import com.devs.demoCours.utils.Genero;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class EstudianteResponse {
    @Schema(example = "1")
    private Long id;
    @Schema(example = "70370695")
    private String dni;
    @Schema(example = "Juan")
    private String name;
    @Schema(example = "Torres Canales")
    private String lastName;
    @Schema(example = "alumno@example.com")
    private String email;
    @Schema(example = "https:miAvatar.com")
    private String avatar;
    @Schema(example = "MASCULINO")
    private Genero genero;
    @Schema(example = "Soy un estudiante con miras al futuro...")
    private String review;
    private LocalDate fechaInscription;
}
