package com.devs.demoCours.utils.exeptions;

public class IdNoExist extends RuntimeException{
    private static final String ERROR_MESSAGE="El id: %s ingresado no se encuentra registrado en la tabla %s. ";
    public IdNoExist(String id,String nameTable){
        super(String.format(ERROR_MESSAGE,id,nameTable));
    }
}
