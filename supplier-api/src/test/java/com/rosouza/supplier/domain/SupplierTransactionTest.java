package com.rosouza.supplier.domain;

import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.nio.charset.StandardCharsets;
import java.util.Random;
import java.util.Set;

import static com.rosouza.supplier.SupplierTransactionTestConstants.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class SupplierTransactionTest {

    private final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    @Test
    void shouldNotValidateWhenIdIsNull() {
        shouldNotValidateByProperty("id");
    }

    @Test
    void shouldNotValidateWhenSupplierIdIsNull() {
        shouldNotValidateByProperty("supplierId");
    }

    @Test
    void shouldNotValidateWhenDateTimeIsNull() {
        shouldNotValidateByProperty("dateTime");
    }

    @Test
    void shouldNotValidateWhenContentIsNull() {
        shouldNotValidateByProperty("content");
    }

    @Test
    void shouldNotValidateWhenContentSizeIsGreaterThan64KB() {
        var supplier = new SupplierTransaction();
        supplier.setId(ID);
        supplier.setSupplierId(SUPPLIER_ID);
        supplier.setDateTime(DATE_TIME);
        supplier.setContent(buildStringWithSize(64001));

        var constraintViolations = validator.validate(supplier);
        assertThat(constraintViolations).hasSize(1);
    }

    @Test
    void shouldValidateWhenContentSizeIsLessThan64kb() {
        var supplier = new SupplierTransaction();

        supplier.setId(ID);
        supplier.setSupplierId(SUPPLIER_ID);
        supplier.setDateTime(DATE_TIME);
        supplier.setContent(buildStringWithSize(63999));

        var constraintViolations = validator.validate(supplier);
        assertThat(constraintViolations).hasSize(0);
    }

    @Test
    void shouldValidateWhenContentSizeIsEqualThan64kb() {
        var supplier = new SupplierTransaction();

        supplier.setId(ID);
        supplier.setSupplierId(SUPPLIER_ID);
        supplier.setDateTime(DATE_TIME);
        supplier.setContent(buildStringWithSize(64000));

        var constraintViolations = validator.validate(supplier);
        assertThat(constraintViolations).hasSize(0);
    }

    private String buildStringWithSize(int bytesSize) {
        return new String(new byte[bytesSize], StandardCharsets.UTF_8);
    }

    @Test
    void shouldValidateWhenAllRequiredFieldsArePresent() {
        var transaction = new SupplierTransaction();

        var constraintViolations = validator.validate(transaction);
        assertThat(constraintViolations).hasSize(4);

        transaction.setId(ID);
        transaction.setSupplierId(SUPPLIER_ID);
        transaction.setDateTime(DATE_TIME);
        transaction.setContent(CONTENT);

        constraintViolations = validator.validate(transaction);
        assertThat(constraintViolations).hasSize(0);
    }

    ConstraintViolation<SupplierTransaction> find(Set<ConstraintViolation<SupplierTransaction>> constraintViolations, String propertyName) {
        return constraintViolations.stream()
            .filter(i -> propertyName.equals(i.getPropertyPath().toString()))
            .findAny()
            .orElse(null);
    }

    private void shouldNotValidateByProperty(String propertyName) {
        var constraintViolations = validator.validate(new SupplierTransaction());
        ConstraintViolation<SupplierTransaction> violation = find(constraintViolations, propertyName);
        assertNotNull(violation);
        assertThat(violation.getMessage()).isEqualTo("must not be null");
    }

}
