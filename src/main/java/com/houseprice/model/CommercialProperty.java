package com.houseprice.model;

/**
 * Commercial Property implementation (Polymorphism)
 */
public class CommercialProperty extends Property {
    private String propertyType = "COMMERCIAL";
    private double rentIncome;
    private boolean hasParking;
    private int maxFloors;

    public CommercialProperty() {
        super();
    }

    public CommercialProperty(String address, double area, int bedrooms, int bathrooms,
                              int yearBuilt, double rentIncome, boolean hasParking, int maxFloors) {
        super(address, area, bedrooms, bathrooms, yearBuilt);
        this.rentIncome = rentIncome;
        this.hasParking = hasParking;
        this.maxFloors = maxFloors;
    }

    @Override
    public double calculatePrice() {
        // Price calculation for commercial properties based on rental income
        double pricePerSqFt = 2000; // $2000 per sq ft for commercial
        double basePrice = area * pricePerSqFt;
        double rentMultiplier = rentIncome * 12; // Annual rent
        double parkingBonus = hasParking ? 50000 : 0;
        double ageDiscount = (2024 - yearBuilt) * 500;
        
        double estimatedPrice = basePrice + (rentMultiplier * 8) + parkingBonus - ageDiscount;
        return Math.max(estimatedPrice, 100000); // Minimum price
    }

    @Override
    public String getPropertyType() {
        return propertyType;
    }

    public double getRentIncome() {
        return rentIncome;
    }

    public void setRentIncome(double rentIncome) {
        this.rentIncome = rentIncome;
    }

    public boolean isHasParking() {
        return hasParking;
    }

    public void setHasParking(boolean hasParking) {
        this.hasParking = hasParking;
    }

    public int getMaxFloors() {
        return maxFloors;
    }

    public void setMaxFloors(int maxFloors) {
        this.maxFloors = maxFloors;
    }
}
