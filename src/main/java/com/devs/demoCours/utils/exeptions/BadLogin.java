package com.devs.demoCours.utils.exeptions;

public class BadLogin extends RuntimeException{
    private static final String ERROR_MESSAGE = "Username o contraseña incorrecta";

    public BadLogin(){
        super(String.format(ERROR_MESSAGE));
    }

}
