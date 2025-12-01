package com.houseprice.util;

import java.util.ArrayList;
import java.util.List;

/**
 * Validation utility for input validation
 * Demonstrates utility pattern
 */
public class ValidationUtil {

    public static boolean isValidAddress(String address) {
        return address != null && !address.trim().isEmpty() && address.length() <= 255;
    }

    public static boolean isValidArea(double area) {
        return area > 0 && area <= 1000000; // Max 1 million sq ft
    }

    public static boolean isValidBedrooms(int bedrooms) {
        return bedrooms >= 0 && bedrooms <= 20;
    }

    public static boolean isValidBathrooms(int bathrooms) {
        return bathrooms >= 0 && bathrooms <= 20;
    }

    public static boolean isValidYearBuilt(int year) {
        int currentYear = 2024;
        return year >= 1800 && year <= currentYear;
    }

    public static boolean isValidPrice(double price) {
        return price >= 0 && price <= 1000000000; // Max 1 billion
    }

    public static List<String> validateProperty(String address, double area, 
                                                 int bedrooms, int bathrooms, int yearBuilt) {
        List<String> errors = new ArrayList<>();

        if (!isValidAddress(address)) {
            errors.add("Invalid address");
        }
        if (!isValidArea(area)) {
            errors.add("Invalid area");
        }
        if (!isValidBedrooms(bedrooms)) {
            errors.add("Invalid bedrooms count");
        }
        if (!isValidBathrooms(bathrooms)) {
            errors.add("Invalid bathrooms count");
        }
        if (!isValidYearBuilt(yearBuilt)) {
            errors.add("Invalid year built");
        }

        return errors;
    }

    public static boolean hasErrors(List<String> errors) {
        return errors != null && !errors.isEmpty();
    }

    public static String getErrorsAsString(List<String> errors) {
        if (errors == null || errors.isEmpty()) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (String error : errors) {
            sb.append("- ").append(error).append("\n");
        }
        return sb.toString();
    }
}
