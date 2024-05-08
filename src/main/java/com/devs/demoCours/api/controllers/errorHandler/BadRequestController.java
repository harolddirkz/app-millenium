package com.devs.demoCours.api.controllers.errorHandler;

import com.devs.demoCours.api.models.responses.errors.ErrorResponse;
import com.devs.demoCours.utils.exeptions.JwtValidationException;
import com.devs.demoCours.utils.exeptions.UsuarioDuplicado;
import com.devs.demoCours.utils.exeptions.UsuarioNoExist;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BadRequestController {
    @ExceptionHandler(UsuarioDuplicado.class)
    public ErrorResponse usuarioDuplicado(UsuarioDuplicado exception) {
        return ErrorResponse.builder()
                .message(exception.getMessage())
                .status(HttpStatus.BAD_REQUEST.name())
                .code(HttpStatus.BAD_REQUEST.value())
                .build();
    }

    @ExceptionHandler(UsuarioNoExist.class)
    public ErrorResponse usuarioNoExist(UsuarioNoExist exception) {
        return ErrorResponse.builder()
                .message(exception.getMessage())
                .status(HttpStatus.BAD_REQUEST.name())
                .code(HttpStatus.BAD_REQUEST.value())
                .build();
    }

    @ExceptionHandler(JwtValidationException.class)
    public ErrorResponse tokenInvalido(JwtValidationException exception) {
        return ErrorResponse.builder()
                .message(exception.getMessage())
                .status(HttpStatus.UNAUTHORIZED.name())
                .code(HttpStatus.UNAUTHORIZED.value())
                .build();

    }
}