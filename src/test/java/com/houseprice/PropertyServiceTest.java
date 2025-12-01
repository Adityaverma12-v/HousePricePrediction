package com.houseprice.test;

import com.houseprice.model.*;
import com.houseprice.service.*;
import com.houseprice.exception.*;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Unit tests for PropertyService
 * Demonstrates OOP, Polymorphism, and Exception Handling
 */
public class PropertyServiceTest {
    private PropertyService propertyService;

    @Before
    public void setUp() {
        propertyService = new PropertyService();
    }

    @Test
    public void testResidentialPropertyPriceCalculation() {
        ResidentialProperty property = new ResidentialProperty(
                "123 Test Street", 2000, 3, 2, 2015, 2, true, true);
        
        double price = property.calculatePrice();
        assertTrue("Price should be positive", price > 0);
        assertTrue("Price should be reasonable for residential", price > 50000);
    }

    @Test
    public void testCommercialPropertyPriceCalculation() {
        CommercialProperty property = new CommercialProperty(
                "456 Business St", 5000, 0, 1, 2010, 5000, true, 5);
        
        double price = property.calculatePrice();
        assertTrue("Price should be positive", price > 0);
        assertTrue("Commercial price should be higher than residential", price > 100000);
    }

    @Test
    public void testIndustrialPropertyPriceCalculation() {
        IndustrialProperty property = new IndustrialProperty(
                "789 Factory Rd", 10000, 0, 1, 2005, 500, true, "INDUSTRIAL");
        
        double price = property.calculatePrice();
        assertTrue("Price should be positive", price > 0);
    }

    @Test
    public void testPolymorphism() {
        Property residential = new ResidentialProperty("123 St", 2000, 3, 2, 2015, 2, true, true);
        Property commercial = new CommercialProperty("456 Ave", 5000, 0, 1, 2010, 5000, true, 5);
        Property industrial = new IndustrialProperty("789 Rd", 10000, 0, 1, 2005, 500, true, "IND");

        assertEquals("Residential property type", "RESIDENTIAL", residential.getPropertyType());
        assertEquals("Commercial property type", "COMMERCIAL", commercial.getPropertyType());
        assertEquals("Industrial property type", "INDUSTRIAL", industrial.getPropertyType());

        // All calculate prices but differently due to polymorphism
        assertTrue("All prices should be calculable", 
                residential.calculatePrice() > 0 && 
                commercial.calculatePrice() > 0 && 
                industrial.calculatePrice() > 0);
    }

    @Test(expected = ValidationException.class)
    public void testValidationException() throws HousePriceException {
        Property invalidProperty = new ResidentialProperty();
        invalidProperty.setAddress(""); // Empty address
        propertyService.addProperty(invalidProperty);
    }

    @Test
    public void testPropertyGettersSetters() {
        ResidentialProperty property = new ResidentialProperty();
        property.setPropertyId(1);
        property.setAddress("Test Address");
        property.setArea(1500);
        property.setBedrooms(3);
        property.setBathrooms(2);
        property.setYearBuilt(2020);
        property.setFloors(2);
        property.setHasGarage(true);
        property.setHasGarden(true);

        assertEquals(1, property.getPropertyId());
        assertEquals("Test Address", property.getAddress());
        assertEquals(1500, property.getArea(), 0.01);
        assertEquals(3, property.getBedrooms());
        assertEquals(2, property.getBathrooms());
        assertEquals(2020, property.getYearBuilt());
        assertEquals(2, property.getFloors());
        assertTrue(property.isHasGarage());
        assertTrue(property.isHasGarden());
    }

    @Test
    public void testPredictionResult() {
        PredictionResult result = new PredictionResult(1, 250000, "LINEAR_REGRESSION");
        
        assertNotNull(result.getPredictionDate());
        assertEquals(1, result.getPropertyId());
        assertEquals(250000, result.getPredictedPrice(), 0.01);
        assertEquals("LINEAR_REGRESSION", result.getAlgorithm());
        assertEquals("PENDING", result.getStatus());
    }

    @Test
    public void testExceptionHierarchy() {
        HousePriceException exception = new ValidationException("Test error", "VALIDATION_ERR");
        assertEquals("VALIDATION_ERR", exception.getErrorCode());
        assertTrue(exception instanceof HousePriceException);
        assertTrue(exception instanceof ValidationException);
    }
}
