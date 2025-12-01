package com.houseprice.model;

/**
 * Industrial Property implementation (Polymorphism)
 */
public class IndustrialProperty extends Property {
    private String propertyType = "INDUSTRIAL";
    private double loadCapacity;
    private boolean hasLoding;
    private String zoneType;

    public IndustrialProperty() {
        super();
    }

    public IndustrialProperty(String address, double area, int bedrooms, int bathrooms,
                              int yearBuilt, double loadCapacity, boolean hasLoding, String zoneType) {
        super(address, area, bedrooms, bathrooms, yearBuilt);
        this.loadCapacity = loadCapacity;
        this.hasLoding = hasLoding;
        this.zoneType = zoneType;
    }

    @Override
    public double calculatePrice() {
        // Price calculation for industrial properties
        double basePrice = area * 800; // $800 per sq ft
        double loadBonus = loadCapacity * 100;
        double lodingBonus = hasLoding ? 75000 : 0;
        double ageDiscount = (2024 - yearBuilt) * 800;
        
        double estimatedPrice = basePrice + loadBonus + lodingBonus - ageDiscount;
        return Math.max(estimatedPrice, 50000); // Minimum price
    }

    @Override
    public String getPropertyType() {
        return propertyType;
    }

    public double getLoadCapacity() {
        return loadCapacity;
    }

    public void setLoadCapacity(double loadCapacity) {
        this.loadCapacity = loadCapacity;
    }

    public boolean isHasLoding() {
        return hasLoding;
    }

    public void setHasLoding(boolean hasLoding) {
        this.hasLoding = hasLoding;
    }

    public String getZoneType() {
        return zoneType;
    }

    public void setZoneType(String zoneType) {
        this.zoneType = zoneType;
    }
}
