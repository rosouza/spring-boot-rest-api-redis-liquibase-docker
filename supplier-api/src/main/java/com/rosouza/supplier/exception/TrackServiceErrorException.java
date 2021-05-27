package com.rosouza.supplier.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR, reason = "supplier.tracking.service.error")
public class TrackServiceErrorException extends RuntimeException {
}
