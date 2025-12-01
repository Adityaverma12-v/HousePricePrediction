package com.houseprice.test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

import com.houseprice.model.PredictionResult;
import com.houseprice.model.ResidentialProperty;
import com.houseprice.service.PricePredictionEngine;

/**
 * Tests for multithreading in PricePredictionEngine
 */
public class PricePredictionEngineTest {
    private PricePredictionEngine engine;

    @Before
    public void setUp() {
        engine = new PricePredictionEngine(4);
    }

    @Test
    public void testConcurrentPredictions() throws Exception {
        ResidentialProperty property = new ResidentialProperty(
                "123 Test St", 2000, 3, 2, 2015, 2, true, true);

        List<PredictionResult> results = engine.predictPrice(property);

        // Should have 3 predictions from 3 algorithms
        assertEquals("Should have 3 algorithm predictions", 3, results.size());

        // All should have different algorithms
        long uniqueAlgorithms = results.stream()
                .map(PredictionResult::getAlgorithm)
                .distinct()
                .count();
        assertEquals("All algorithms should be unique", 3, uniqueAlgorithms);

        // All should have positive prices
        for (PredictionResult result : results) {
            assertTrue("Price should be positive", result.getPredictedPrice() > 0);
            assertTrue("Accuracy should be positive", result.getAccuracy() > 0);
            assertTrue("Accuracy should be <= 100", result.getAccuracy() <= 100);
        }
    }

    @Test
    public void testCacheOperations() throws Exception {
        ResidentialProperty property = new ResidentialProperty(
                "456 Cache St", 1800, 2, 1, 2018, 1, false, true);

        // Make predictions
        engine.predictPrice(property);
        
        // Retrieve from cache
        List<PredictionResult> cached = engine.getCachedPredictions(property.getPropertyId());
        assertEquals("Cache should contain predictions", 3, cached.size());

        // Clear cache
        engine.clearCache();
        cached = engine.getCachedPredictions(property.getPropertyId());
        assertEquals("Cache should be empty", 0, cached.size());
    }
}
