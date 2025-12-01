package com.houseprice.model;

import java.time.LocalDateTime;

/**
 * Prediction Result model for storing price predictions
 * Demonstrates use of Collections and Generics
 */
public class PredictionResult {
    private int predictionId;
    private int propertyId;
    private double predictedPrice;
    private double actualPrice;
    private double accuracy;
    private LocalDateTime predictionDate;
    private String algorithm;
    private String status;

    public PredictionResult() {
    }

    public PredictionResult(int propertyId, double predictedPrice, String algorithm) {
        this.propertyId = propertyId;
        this.predictedPrice = predictedPrice;
        this.algorithm = algorithm;
        this.predictionDate = LocalDateTime.now();
        this.status = "PENDING";
    }

    // Getters and Setters
    public int getPredictionId() {
        return predictionId;
    }

    public void setPredictionId(int predictionId) {
        this.predictionId = predictionId;
    }

    public int getPropertyId() {
        return propertyId;
    }

    public void setPropertyId(int propertyId) {
        this.propertyId = propertyId;
    }

    public double getPredictedPrice() {
        return predictedPrice;
    }

    public void setPredictedPrice(double predictedPrice) {
        this.predictedPrice = predictedPrice;
    }

    public double getActualPrice() {
        return actualPrice;
    }

    public void setActualPrice(double actualPrice) {
        this.actualPrice = actualPrice;
    }

    public double getAccuracy() {
        return accuracy;
    }

    public void setAccuracy(double accuracy) {
        this.accuracy = accuracy;
    }

    public LocalDateTime getPredictionDate() {
        return predictionDate;
    }

    public void setPredictionDate(LocalDateTime predictionDate) {
        this.predictionDate = predictionDate;
    }

    public String getAlgorithm() {
        return algorithm;
    }

    public void setAlgorithm(String algorithm) {
        this.algorithm = algorithm;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "PredictionResult{" +
                "predictionId=" + predictionId +
                ", propertyId=" + propertyId +
                ", predictedPrice=" + predictedPrice +
                ", actualPrice=" + actualPrice +
                ", accuracy=" + accuracy +
                ", algorithm='" + algorithm + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
