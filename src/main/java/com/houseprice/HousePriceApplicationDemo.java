package com.houseprice;

import java.util.ArrayList;
import java.util.List;

import com.houseprice.model.CommercialProperty;
import com.houseprice.model.IndustrialProperty;
import com.houseprice.model.PredictionResult;
import com.houseprice.model.Property;
import com.houseprice.model.ResidentialProperty;
import com.houseprice.service.PricePredictionEngine;

/**
 * Standalone Demo - Works without database
 * Demonstrates OOP, Collections, Multithreading, and Generics
 */
public class HousePriceApplicationDemo {
    
    public static void main(String[] args) {
        System.out.println("\n╔════════════════════════════════════════╗");
        System.out.println("║  House Price Prediction System - Demo  ║");
        System.out.println("║  Compiled with Simple Java (No Maven)  ║");
        System.out.println("╚════════════════════════════════════════╝\n");

        try {
            System.out.println("█ STEP 1: Creating Properties (Polymorphism)\n");
            
            // Create properties - demonstrating inheritance
            ResidentialProperty residential = new ResidentialProperty(
                    "123 Oak Street", 2000, 3, 2, 2015, 2, true, true);
            residential.setPropertyId(1);
            
            CommercialProperty commercial = new CommercialProperty(
                    "456 Business Ave", 5000, 0, 1, 2010, 5000, true, 5);
            commercial.setPropertyId(2);
            
            IndustrialProperty industrial = new IndustrialProperty(
                    "789 Factory Road", 10000, 0, 1, 2005, 500, true, "INDUSTRIAL");
            industrial.setPropertyId(3);

            // Store in list - Collections feature
            List<Property> properties = new ArrayList<>();
            properties.add(residential);
            properties.add(commercial);
            properties.add(industrial);

            System.out.println("Created " + properties.size() + " sample properties:\n");

            // Display properties with polymorphic calculatePrice()
            System.out.println("█ STEP 2: Property Details & Price Calculations\n");
            for (Property prop : properties) {
                System.out.println("  ┌─ " + prop.getPropertyType());
                System.out.println("  │  Address: " + prop.getAddress());
                System.out.println("  │  Area: " + prop.getArea() + " sqft");
                System.out.println("  │  Built: " + prop.getYearBuilt());
                System.out.printf("  │  💰 Calculated Price: $%.2f\n", prop.calculatePrice());
                System.out.println("  └─────────────────────────────────\n");
            }

            // Calculate statistics using streams - Generics and Collections
            System.out.println("█ STEP 3: Price Statistics (Streams & Generics)\n");
            double totalPrice = properties.stream()
                    .mapToDouble(Property::calculatePrice)
                    .sum();
            double avgPrice = totalPrice / properties.size();
            double maxPrice = properties.stream()
                    .mapToDouble(Property::calculatePrice)
                    .max()
                    .orElse(0);
            
            System.out.printf("  Total Properties: %d\n", properties.size());
            System.out.printf("  Total Value: $%.2f\n", totalPrice);
            System.out.printf("  Average Price: $%.2f\n", avgPrice);
            System.out.printf("  Highest Price: $%.2f\n", maxPrice);

            // Demonstrate Generics with type-safe filtering
            System.out.println("\n█ STEP 4: Generic Type Filtering\n");
            List<ResidentialProperty> residentialProps = filterByType(properties, ResidentialProperty.class);
            System.out.println("  Residential Properties Found: " + residentialProps.size());
            for (ResidentialProperty prop : residentialProps) {
                System.out.println("    • " + prop.getAddress());
            }

            // Demonstrate concurrent multithreading
            System.out.println("\n█ STEP 5: Concurrent Price Predictions (Multithreading)\n");
            System.out.println("  Starting 3 prediction algorithms in parallel...\n");
            
            PricePredictionEngine engine = new PricePredictionEngine(4);
            
            for (Property prop : properties) {
                System.out.println("  Predicting for: " + prop.getAddress());
                List<PredictionResult> predictions = engine.predictPrice(prop);
                
                for (PredictionResult pred : predictions) {
                    System.out.printf("    ✓ %s: $%.2f (%.0f%% accurate)\n",
                            pred.getAlgorithm(),
                            pred.getPredictedPrice(),
                            pred.getAccuracy());
                }
                System.out.println();
            }

            // Exception handling demonstration
            System.out.println("█ STEP 6: Exception Handling\n");
            try {
                ResidentialProperty invalidProp = new ResidentialProperty(
                        "", 0, 0, 0, 2000, 0, false, false);
            } catch (Exception e) {
                System.out.println("  ✓ Caught validation error: " + e.getMessage());
            }

            System.out.println("\n╔════════════════════════════════════════╗");
            System.out.println("║     ✅ Demo Completed Successfully!    ║");
            System.out.println("║                                        ║");
            System.out.println("║  All 15 Java classes working without   ║");
            System.out.println("║  Maven or external dependencies        ║");
            System.out.println("╚════════════════════════════════════════╝\n");

            System.out.println("Features Demonstrated:");
            System.out.println("  ✓ OOP: Inheritance (Property subclasses)");
            System.out.println("  ✓ OOP: Polymorphism (calculatePrice())");
            System.out.println("  ✓ OOP: Exception Handling");
            System.out.println("  ✓ Collections: ArrayList, List");
            System.out.println("  ✓ Generics: Type-safe filtering <T>");
            System.out.println("  ✓ Streams: map, filter, sum, max");
            System.out.println("  ✓ Multithreading: ExecutorService with 3 concurrent algorithms");
            System.out.println("  ✓ Interfaces: IRepository pattern (not shown in demo)\n");

        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Generic method demonstrating type-safe filtering
     * @param <T> Type parameter
     * @param list List to filter
     * @param type Class to filter by
     * @return Filtered list
     */
    public static <T> List<T> filterByType(List<?> list, Class<T> type) {
        List<T> result = new ArrayList<>();
        for (Object obj : list) {
            if (type.isInstance(obj)) {
                result.add(type.cast(obj));
            }
        }
        return result;
    }
}
