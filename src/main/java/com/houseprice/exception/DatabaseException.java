package com.houseprice.exception;

/**
 * Exception for database-related errors
 */
public class DatabaseException extends HousePriceException {
    public DatabaseException(String message) {
        super(message, "DB_ERROR");
    }

    public DatabaseException(String message, Throwable cause) {
        super(message, "DB_ERROR", cause);
    }
}
