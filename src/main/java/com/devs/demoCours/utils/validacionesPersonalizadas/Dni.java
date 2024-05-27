package com.devs.demoCours.utils.validacionesPersonalizadas;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy =DniValidation.class)//futura clase que contendrá la lógica de la validación
@Target({ElementType.METHOD,ElementType.FIELD})//Destino de nuestra anotación a métodos o campos
@Retention(RetentionPolicy.RUNTIME)//check la anotación en tiempo de ejecución
public @interface Dni {
    //definir valor por defecto
    /*public String value() default "00";*/
    //definir el mensaje de error
public String message() default "DNI invalido";
    //definir los grupos
    Class<?>[] groups() default {};

    //definir los payloads
    Class<? extends Payload>[] payload() default {};
}