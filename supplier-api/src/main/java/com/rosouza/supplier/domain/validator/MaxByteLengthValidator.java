package com.rosouza.supplier.domain.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.nio.charset.StandardCharsets;

public class MaxByteLengthValidator implements ConstraintValidator<MaxByteLength, String> {

    private int maxLength;

    public void initialize(MaxByteLength constraintAnnotation) {
        this.maxLength = constraintAnnotation.value();
    }
    public boolean isValid(String object, ConstraintValidatorContext constraintContext) {
        return object == null || object.getBytes(StandardCharsets.UTF_8).length <= this.maxLength;
    }
}
