package com.houseprice.service;

import java.util.List;
import java.util.stream.Collectors;

import com.houseprice.dao.PredictionResultDAO;
import com.houseprice.dao.PropertyDAO;
import com.houseprice.exception.DatabaseException;
import com.houseprice.exception.HousePriceException;
import com.houseprice.exception.ValidationException;
import com.houseprice.model.PredictionResult;
import com.houseprice.model.Property;

/**
 * Property Service - Business logic layer
 * Handles property operations and price calculations
 */
public class PropertyService {
    private PropertyDAO propertyDAO;
    private PredictionResultDAO predictionDAO;

    public PropertyService() {
        this.propertyDAO = new PropertyDAO();
        this.predictionDAO = new PredictionResultDAO();
    }

    /**
     * Add new property to database
     */
    public void addProperty(Property property) throws HousePriceException {
        try {
            if (property == null || property.getAddress() == null || property.getAddress().isEmpty()) {
                throw new ValidationException("Property address cannot be empty");
            }
            
            if (property.getArea() <= 0 || property.getBedrooms() < 0 || property.getBathrooms() < 0) {
                throw new ValidationException("Invalid property dimensions");
            }
            
            if (!propertyDAO.save(property)) {
                throw new DatabaseException("Failed to save property to database");
            }
        } catch (HousePriceException e) {
            throw e;
        } catch (Exception e) {
            throw new DatabaseException("Error adding property: " + e.getMessage(), e);
        }
    }

    /**
     * Get all properties (using Generics from IRepository)
     */
    public List<Property> getAllProperties() throws HousePriceException {
        try {
            return propertyDAO.findAll();
        } catch (Exception e) {
            throw new DatabaseException("Error retrieving properties: " + e.getMessage(), e);
        }
    }

    /**
     * Get property by ID
     */
    public Property getPropertyById(int propertyId) throws HousePriceException {
        try {
            Property property = propertyDAO.findById(propertyId);
            if (property == null) {
                throw new ValidationException("Property with ID " + propertyId + " not found");
            }
            return property;
        } catch (HousePriceException e) {
            throw e;
        } catch (Exception e) {
            throw new DatabaseException("Error retrieving property: " + e.getMessage(), e);
        }
    }

    /**
     * Update property details
     */
    public void updateProperty(Property property) throws HousePriceException {
        try {
            if (!propertyDAO.update(property)) {
                throw new DatabaseException("Failed to update property in database");
            }
        } catch (HousePriceException e) {
            throw e;
        } catch (Exception e) {
            throw new DatabaseException("Error updating property: " + e.getMessage(), e);
        }
    }

    /**
     * Delete property
     */
    public void deleteProperty(int propertyId) throws HousePriceException {
        try {
            if (!propertyDAO.delete(propertyId)) {
                throw new DatabaseException("Failed to delete property from database");
            }
        } catch (HousePriceException e) {
            throw e;
        } catch (Exception e) {
            throw new DatabaseException("Error deleting property: " + e.getMessage(), e);
        }
    }

    /**
     * Get properties by type (Collections & Generics)
     */
    public <T extends Property> List<T> getPropertiesByType(Class<T> type) throws HousePriceException {
        try {
            List<Property> allProperties = propertyDAO.findAll();
            return allProperties.stream()
                    .filter(p -> type.isInstance(p))
                    .map(type::cast)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new DatabaseException("Error retrieving properties by type: " + e.getMessage(), e);
        }
    }

    /**
     * Calculate average price for properties
     */
    public double getAveragePredictedPrice() throws HousePriceException {
        try {
            List<Property> properties = propertyDAO.findAll();
            return properties.stream()
                    .mapToDouble(Property::calculatePrice)
                    .average()
                    .orElse(0);
        } catch (Exception e) {
            throw new DatabaseException("Error calculating average price: " + e.getMessage(), e);
        }
    }

    /**
     * Get total count of properties
     */
    public int getTotalPropertiesCount() throws HousePriceException {
        try {
            return propertyDAO.count();
        } catch (Exception e) {
            throw new DatabaseException("Error counting properties: " + e.getMessage(), e);
        }
    }

    /**
     * Save prediction result
     */
    public void savePredictionResult(PredictionResult result) throws HousePriceException {
        try {
            if (!predictionDAO.save(result)) {
                throw new DatabaseException("Failed to save prediction result");
            }
        } catch (HousePriceException e) {
            throw e;
        } catch (Exception e) {
            throw new DatabaseException("Error saving prediction result: " + e.getMessage(), e);
        }
    }

    /**
     * Get prediction results for a property
     */
    public List<PredictionResult> getPredictionResults(int propertyId) throws HousePriceException {
        try {
            List<PredictionResult> allResults = predictionDAO.findAll();
            return allResults.stream()
                    .filter(r -> r.getPropertyId() == propertyId)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new DatabaseException("Error retrieving predictions: " + e.getMessage(), e);
        }
    }
}
