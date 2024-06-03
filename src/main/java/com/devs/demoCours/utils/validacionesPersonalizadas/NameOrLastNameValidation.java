package com.devs.demoCours.utils.validacionesPersonalizadas;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class NameOrLastNameValidation implements ConstraintValidator<NameOrLastName,String> {
    @Override
    public void initialize(NameOrLastName nameOrLastName) {
        ConstraintValidator.super.initialize(nameOrLastName);
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {

        return value != null && value.matches("[A-Za-zÁÉÍÓÚáéíóúÑñ ]+");

    }
}
