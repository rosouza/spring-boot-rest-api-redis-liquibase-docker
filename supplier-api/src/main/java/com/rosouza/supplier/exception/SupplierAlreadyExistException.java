package com.rosouza.supplier.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT, reason = "supplier.already-exist.error")
public class SupplierAlreadyExistException extends RuntimeException {
}
