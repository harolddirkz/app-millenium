package com.devs.demoCours.utils.validacionesPersonalizadas;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class DniValidation implements ConstraintValidator<Dni,String> {

    @Override
    public void initialize(Dni dni) {
        ConstraintValidator.super.initialize(dni);
    }

    @Override
    public boolean isValid(String dni, ConstraintValidatorContext context) {

        return dni!=null && (dni.matches("[0-9]+")&& dni.length()==8);
    }
}
