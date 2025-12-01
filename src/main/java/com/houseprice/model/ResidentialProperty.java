package com.houseprice.model;

/**
 * Residential Property implementation (Polymorphism)
 */
public class ResidentialProperty extends Property {
    private String propertyType = "RESIDENTIAL";
    private int floors;
    private boolean hasGarage;
    private boolean hasGarden;

    public ResidentialProperty() {
        super();
    }

    public ResidentialProperty(String address, double area, int bedrooms, int bathrooms, 
                               int yearBuilt, int floors, boolean hasGarage, boolean hasGarden) {
        super(address, area, bedrooms, bathrooms, yearBuilt);
        this.floors = floors;
        this.hasGarage = hasGarage;
        this.hasGarden = hasGarden;
    }

    @Override
    public double calculatePrice() {
        // Base price calculation for residential properties
        double basePrice = area * 1500; // $1500 per sq ft
        double pricePerBedroom = bedrooms * 50000;
        double ageDiscount = (2024 - yearBuilt) * 1000;
        double garageBonus = hasGarage ? 20000 : 0;
        double gardenBonus = hasGarden ? 15000 : 0;
        
        double estimatedPrice = basePrice + pricePerBedroom + garageBonus + gardenBonus - ageDiscount;
        return Math.max(estimatedPrice, 50000); // Minimum price
    }

    @Override
    public String getPropertyType() {
        return propertyType;
    }

    public int getFloors() {
        return floors;
    }

    public void setFloors(int floors) {
        this.floors = floors;
    }

    public boolean isHasGarage() {
        return hasGarage;
    }

    public void setHasGarage(boolean hasGarage) {
        this.hasGarage = hasGarage;
    }

    public boolean isHasGarden() {
        return hasGarden;
    }

    public void setHasGarden(boolean hasGarden) {
        this.hasGarden = hasGarden;
    }
}
