package com.rosouza.supplier.domain.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({ FIELD })
@Retention(RUNTIME)
@Constraint(validatedBy = MaxByteLengthValidator.class)
@Documented
public @interface MaxByteLength {

    String message() default "{org.hibernate.validator.string.max.bytes.length.64kb}";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };

    int value();

}
