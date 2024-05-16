package com.devs.demoCours.api.models.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class TakeExamenCreateRequest implements Serializable {
    private Long idExamenSession;
    private Long idInscription;
}
