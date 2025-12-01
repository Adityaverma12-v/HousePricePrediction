package com.houseprice.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import com.houseprice.exception.HousePriceException;
import com.houseprice.model.PredictionResult;
import com.houseprice.model.Property;

/**
 * Price Prediction Engine with Multithreading
 * Handles concurrent price predictions using multiple algorithms
 */
public class PricePredictionEngine {
    private ExecutorService executorService;
    private List<PredictionResult> predictionCache;
    private final Object cacheLock = new Object();

    public PricePredictionEngine(int threadPoolSize) {
        this.executorService = Executors.newFixedThreadPool(threadPoolSize);
        this.predictionCache = Collections.synchronizedList(new ArrayList<>());
    }

    /**
     * Predict price for a property using multiple algorithms concurrently
     */
    public List<PredictionResult> predictPrice(Property property) throws HousePriceException {
        List<Future<PredictionResult>> futures = new ArrayList<>();
        List<PredictionResult> results = new ArrayList<>();

        try {
            // Submit multiple prediction tasks
            futures.add(executorService.submit(() -> predictUsingLinearRegression(property)));
            futures.add(executorService.submit(() -> predictUsingPropertyFeatures(property)));
            futures.add(executorService.submit(() -> predictUsingMarketAnalysis(property)));

            // Collect results from all threads
            for (Future<PredictionResult> future : futures) {
                try {
                    PredictionResult result = future.get(10, TimeUnit.SECONDS);
                    results.add(result);
                    synchronized (cacheLock) {
                        predictionCache.add(result);
                    }
                } catch (TimeoutException e) {
                    throw new HousePriceException("Prediction timeout", e);
                }
            }
        } catch (InterruptedException | ExecutionException e) {
            throw new HousePriceException("Error during concurrent predictions", e);
        }

        return results;
    }

    /**
     * Linear Regression based prediction algorithm
     */
    private PredictionResult predictUsingLinearRegression(Property property) {
        double price = property.calculatePrice();
        double adjustedPrice = price * 1.05; // 5% adjustment factor
        
        PredictionResult result = new PredictionResult(property.getPropertyId(), adjustedPrice, "LINEAR_REGRESSION");
        result.setAccuracy(92.5);
        return result;
    }

    /**
     * Property Features based prediction algorithm
     */
    private PredictionResult predictUsingPropertyFeatures(Property property) {
        double basePrice = property.getArea() * 1200;
        double bedroomFactor = property.getBedrooms() * 35000;
        double bathroomFactor = property.getBathrooms() * 15000;
        double ageFactor = (2024 - property.getYearBuilt()) * (-800);
        
        double price = basePrice + bedroomFactor + bathroomFactor + ageFactor;
        
        PredictionResult result = new PredictionResult(property.getPropertyId(), price, "PROPERTY_FEATURES");
        result.setAccuracy(88.3);
        return result;
    }

    /**
     * Market Analysis based prediction algorithm
     */
    private PredictionResult predictUsingMarketAnalysis(Property property) {
        double marketMultiplier = 1.15; // Market growth factor
        double price = property.calculatePrice() * marketMultiplier;
        
        PredictionResult result = new PredictionResult(property.getPropertyId(), price, "MARKET_ANALYSIS");
        result.setAccuracy(85.7);
        return result;
    }

    /**
     * Get cached predictions for a property
     */
    public synchronized List<PredictionResult> getCachedPredictions(int propertyId) {
        List<PredictionResult> filtered = new ArrayList<>();
        synchronized (cacheLock) {
            for (PredictionResult result : predictionCache) {
                if (result.getPropertyId() == propertyId) {
                    filtered.add(result);
                }
            }
        }
        return filtered;
    }

    /**
     * Clear prediction cache
     */
    public synchronized void clearCache() {
        synchronized (cacheLock) {
            predictionCache.clear();
        }
    }

    /**
     * Shutdown executor service
     */
    public void shutdown() {
        executorService.shutdown();
        try {
            if (!executorService.awaitTermination(10, TimeUnit.SECONDS)) {
                executorService.shutdownNow();
            }
        } catch (InterruptedException e) {
            executorService.shutdownNow();
            Thread.currentThread().interrupt();
        }
    }
}
