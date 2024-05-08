package com.devs.demoCours.utils.exeptions;

public class UsuarioNoAutorizado  extends RuntimeException{
    private static final String ERROR_MESSAGE = "El dni/id %s: Proporcionado, no cuenta con los permisos necesarios";

    public UsuarioNoAutorizado(String dni){
        super(String.format(ERROR_MESSAGE,dni));
    }
}
