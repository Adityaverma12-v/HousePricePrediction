package com.houseprice.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Logging utility for the application
 * Demonstrates utility class pattern
 */
public class Logger {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    private static final boolean DEBUG = true;

    public static void info(String message) {
        log("INFO", message, null);
    }

    public static void error(String message) {
        log("ERROR", message, null);
    }

    public static void error(String message, Throwable throwable) {
        log("ERROR", message, throwable);
    }

    public static void debug(String message) {
        if (DEBUG) {
            log("DEBUG", message, null);
        }
    }

    public static void warn(String message) {
        log("WARN", message, null);
    }

    private static void log(String level, String message, Throwable throwable) {
        String timestamp = LocalDateTime.now().format(formatter);
        System.out.println("[" + timestamp + "] [" + level + "] " + message);
        
        if (throwable != null) {
            throwable.printStackTrace();
        }
    }
}
