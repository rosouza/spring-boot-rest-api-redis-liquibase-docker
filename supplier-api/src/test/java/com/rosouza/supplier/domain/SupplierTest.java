package com.rosouza.supplier.domain;

import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

import static com.rosouza.supplier.SupplierTestConstants.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class SupplierTest {

    private final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    @Test
    void shouldNotValidateWhenNameIsNull() {
        shouldNotValidateByProperty("name");
    }

    @Test
    void shouldNotValidateWhenCountryIsNull() {
        shouldNotValidateByProperty("country");
    }

    @Test
    void shouldNotValidateWhenStateIsNull() {
        shouldNotValidateByProperty("state");
    }

    @Test
    void shouldNotValidateWhenCityIsNull() {
        shouldNotValidateByProperty("city");
    }

    @Test
    void shouldNotValidateWhenAddressIsNull() {
        shouldNotValidateByProperty("address");
    }

    @Test
    void shouldValidateWhenAllRequiredFieldsArePresent() {
        var supplier = new Supplier();

        var constraintViolations = validator.validate(supplier);
        assertThat(constraintViolations).hasSize(5);

        supplier.setName(NAME);
        supplier.setCountry(COUNTRY);
        supplier.setState(STATE);
        supplier.setCity(CITY);
        supplier.setAddress(ADDRESS);

        constraintViolations = validator.validate(supplier);
        assertThat(constraintViolations).hasSize(0);
    }

    ConstraintViolation<Supplier> find(Set<ConstraintViolation<Supplier>> constraintViolations, String propertyName) {
        return constraintViolations.stream()
            .filter(i -> propertyName.equals(i.getPropertyPath().toString()))
            .findAny()
            .orElse(null);
    }

    private void shouldNotValidateByProperty(String propertyName) {
        var constraintViolations = validator.validate(new Supplier());
        ConstraintViolation<Supplier> violation = find(constraintViolations, propertyName);
        assertNotNull(violation);
        assertThat(violation.getMessage()).isEqualTo("must not be null");
    }

}
