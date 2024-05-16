package com.devs.demoCours.api.models.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class TakeExamenUpdateRequest {
    private Long idExamenSession;
    private Long idInscription;
    private Float punctuation;

}
