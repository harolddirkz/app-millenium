package com.devs.demoCours.api.models.responses.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ModuloAndSessionResponse {
    private ModuloResponse modulo;
    private List<SessionResponse> session;
}
