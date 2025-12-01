package com.houseprice.exception;

/**
 * Exception for validation errors
 */
public class ValidationException extends HousePriceException {
    public ValidationException(String message) {
        super(message, "VALIDATION_ERROR");
    }

    public ValidationException(String message, Throwable cause) {
        super(message, "VALIDATION_ERROR", cause);
    }
}
