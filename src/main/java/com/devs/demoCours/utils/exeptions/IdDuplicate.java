package com.devs.demoCours.utils.exeptions;

public class IdDuplicate extends RuntimeException{
    private static final String ERROR_MESSAGE = "%s";

    public IdDuplicate(String message){
        super(String.format(ERROR_MESSAGE,message));
    }
}
