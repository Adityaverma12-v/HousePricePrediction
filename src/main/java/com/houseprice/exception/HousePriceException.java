package com.houseprice.exception;

/**
 * Custom exception for House Price Prediction application
 * Handles all application-specific exceptions with proper error messages
 */
public class HousePriceException extends Exception {
    private String errorCode;
    private Throwable cause;

    public HousePriceException(String message) {
        super(message);
        this.errorCode = "GENERIC_ERROR";
    }

    public HousePriceException(String message, String errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public HousePriceException(String message, Throwable cause) {
        super(message, cause);
        this.cause = cause;
        this.errorCode = "GENERIC_ERROR";
    }

    public HousePriceException(String message, String errorCode, Throwable cause) {
        super(message, cause);
        this.errorCode = errorCode;
        this.cause = cause;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    @Override
    public String toString() {
        return "HousePriceException{" +
                "errorCode='" + errorCode + '\'' +
                ", message='" + getMessage() + '\'' +
                '}';
    }
}
