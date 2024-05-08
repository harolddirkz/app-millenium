package com.devs.demoCours.utils.exeptions;

public class JwtValidationException extends RuntimeException{
    public JwtValidationException(String message) {
        super(message);
    }
}
