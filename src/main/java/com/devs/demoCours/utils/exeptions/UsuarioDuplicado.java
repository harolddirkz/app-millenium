package com.devs.demoCours.utils.exeptions;

public class UsuarioDuplicado extends RuntimeException{
    private static final String ERROR_MESSAGE = "El dni/id %s que intenta ingresar, ya se encuentra registrado en el sistema";

    public UsuarioDuplicado(String dni){
        super(String.format(ERROR_MESSAGE,dni));
    }
}
