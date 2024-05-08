package com.devs.demoCours.utils.exeptions;

public class UsuarioNoExist extends RuntimeException{
    private static final String ERROR_MESSAGE = "El dni/id:  %s, que intenta ingresar, no se encuentra registrado en el sistema";
 public UsuarioNoExist(String dni){super(String.format(ERROR_MESSAGE,dni));}
}
