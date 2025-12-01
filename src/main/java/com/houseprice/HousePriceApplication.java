package com.houseprice;

import com.houseprice.exception.DatabaseException;
import com.houseprice.exception.HousePriceException;
import com.houseprice.exception.ValidationException;
import com.houseprice.model.CommercialProperty;
import com.houseprice.model.IndustrialProperty;
import com.houseprice.model.PredictionResult;
import com.houseprice.model.Property;
import com.houseprice.model.ResidentialProperty;
import com.houseprice.service.PricePredictionEngine;
import com.houseprice.service.PropertyService;

/**
 * Main Application Class - Demonstrates all OOP concepts
 * Entry point for the House Price Prediction System
 */
public class HousePriceApplication {
    
    public static void main(String[] args) {
        System.out.println("=== House Price Prediction System ===");
        System.out.println("Starting application...\n");

        try {
            // Initialize services
            PropertyService propertyService = new PropertyService();
            PricePredictionEngine predictionEngine = new PricePredictionEngine(4);

            // Create sample properties demonstrating polymorphism
            System.out.println("Creating sample properties...");
            ResidentialProperty residential = new ResidentialProperty(
                    "123 Oak Street", 2000, 3, 2, 2015, 2, true, true);
            
            CommercialProperty commercial = new CommercialProperty(
                    "456 Business Ave", 5000, 0, 1, 2010, 5000, true, 5);
            
            IndustrialProperty industrial = new IndustrialProperty(
                    "789 Factory Road", 10000, 0, 1, 2005, 500, true, "INDUSTRIAL");

            // Add properties to database
            System.out.println("Adding properties to database...");
            propertyService.addProperty(residential);
            propertyService.addProperty(commercial);
            propertyService.addProperty(industrial);

            // Retrieve and display properties
            System.out.println("\n=== All Properties ===");
            java.util.List<Property> properties = propertyService.getAllProperties();
            for (Property prop : properties) {
                System.out.println(prop);
                System.out.println("Calculated Price: $" + String.format("%.2f", prop.calculatePrice()));
                System.out.println();
            }

            // Calculate average price
            System.out.println("=== Price Statistics ===");
            double avgPrice = propertyService.getAveragePredictedPrice();
            int totalCount = propertyService.getTotalPropertiesCount();
            System.out.println("Total Properties: " + totalCount);
            System.out.println("Average Price: $" + String.format("%.2f", avgPrice));

            // Demonstrate multithreading with concurrent predictions
            System.out.println("\n=== Concurrent Price Predictions (Multithreading) ===");
            if (!properties.isEmpty()) {
                Property prop = properties.get(0);
                System.out.println("Predicting prices for: " + prop.getAddress());
                
                java.util.List<PredictionResult> predictions = predictionEngine.predictPrice(prop);
                for (PredictionResult pred : predictions) {
                    System.out.println("Algorithm: " + pred.getAlgorithm() + 
                                     " | Price: $" + String.format("%.2f", pred.getPredictedPrice()) +
                                     " | Accuracy: " + String.format("%.1f%%", pred.getAccuracy()));
                    propertyService.savePredictionResult(pred);
                }
            }

            // Demonstrate generics with property filtering
            System.out.println("\n=== Residential Properties (Generics) ===");
            java.util.List<ResidentialProperty> residentialProps = 
                    propertyService.getPropertiesByType(ResidentialProperty.class);
            System.out.println("Found " + residentialProps.size() + " residential properties");

            System.out.println("\n=== Application completed successfully ===");

        } catch (ValidationException e) {
            System.err.println("Validation Error: " + e.getMessage());
            System.err.println("Error Code: " + e.getErrorCode());
        } catch (DatabaseException e) {
            System.err.println("Database Error: " + e.getMessage());
            System.err.println("Error Code: " + e.getErrorCode());
        } catch (HousePriceException e) {
            System.err.println("Application Error: " + e.getMessage());
            System.err.println(e.toString());
        } catch (Exception e) {
            System.err.println("Unexpected Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
