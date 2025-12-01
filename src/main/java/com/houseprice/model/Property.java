package com.houseprice.model;

import java.io.Serializable;

/**
 * Abstract base class for property entities
 * Demonstrates inheritance and OOP principles
 */
public abstract class Property implements Serializable {
    private static final long serialVersionUID = 1L;
    
    protected int propertyId;
    protected String address;
    protected double area;
    protected int bedrooms;
    protected int bathrooms;
    protected int yearBuilt;
    protected String status;

    public Property() {
    }

    public Property(String address, double area, int bedrooms, int bathrooms, int yearBuilt) {
        this.address = address;
        this.area = area;
        this.bedrooms = bedrooms;
        this.bathrooms = bathrooms;
        this.yearBuilt = yearBuilt;
        this.status = "ACTIVE";
    }

    // Abstract method for calculating price (polymorphism)
    public abstract double calculatePrice();

    // Abstract method for getting property type
    public abstract String getPropertyType();

    // Getters and Setters
    public int getPropertyId() {
        return propertyId;
    }

    public void setPropertyId(int propertyId) {
        this.propertyId = propertyId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getArea() {
        return area;
    }

    public void setArea(double area) {
        this.area = area;
    }

    public int getBedrooms() {
        return bedrooms;
    }

    public void setBedrooms(int bedrooms) {
        this.bedrooms = bedrooms;
    }

    public int getBathrooms() {
        return bathrooms;
    }

    public void setBathrooms(int bathrooms) {
        this.bathrooms = bathrooms;
    }

    public int getYearBuilt() {
        return yearBuilt;
    }

    public void setYearBuilt(int yearBuilt) {
        this.yearBuilt = yearBuilt;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Property{" +
                "propertyId=" + propertyId +
                ", address='" + address + '\'' +
                ", area=" + area +
                ", bedrooms=" + bedrooms +
                ", bathrooms=" + bathrooms +
                ", yearBuilt=" + yearBuilt +
                ", type='" + getPropertyType() + '\'' +
                ", estimatedPrice=" + calculatePrice() +
                '}';
    }
}
