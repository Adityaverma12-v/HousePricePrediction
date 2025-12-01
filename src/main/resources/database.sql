-- House Price Prediction Database Schema
-- Execute this script to create the required tables

-- Create Database
CREATE DATABASE IF NOT EXISTS houseprice_db;
USE houseprice_db;

-- Create Properties Table
CREATE TABLE IF NOT EXISTS properties (
    property_id INT AUTO_INCREMENT PRIMARY KEY,
    address VARCHAR(255) NOT NULL,
    area DOUBLE NOT NULL,
    bedrooms INT NOT NULL,
    bathrooms INT NOT NULL,
    year_built INT NOT NULL,
    property_type VARCHAR(50) NOT NULL,
    status VARCHAR(50) DEFAULT 'ACTIVE',
    created_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    INDEX idx_type (property_type),
    INDEX idx_status (status)
);

-- Create Prediction Results Table
CREATE TABLE IF NOT EXISTS prediction_results (
    prediction_id INT AUTO_INCREMENT PRIMARY KEY,
    property_id INT NOT NULL,
    predicted_price DOUBLE NOT NULL,
    actual_price DOUBLE,
    accuracy DOUBLE,
    algorithm VARCHAR(100) NOT NULL,
    status VARCHAR(50) DEFAULT 'PENDING',
    prediction_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (property_id) REFERENCES properties(property_id) ON DELETE CASCADE,
    INDEX idx_property_id (property_id),
    INDEX idx_algorithm (algorithm),
    INDEX idx_date (prediction_date)
);

-- Insert Sample Data
INSERT INTO properties (address, area, bedrooms, bathrooms, year_built, property_type, status) VALUES
('123 Oak Street', 2000, 3, 2, 2015, 'RESIDENTIAL', 'ACTIVE'),
('456 Business Ave', 5000, 0, 1, 2010, 'COMMERCIAL', 'ACTIVE'),
('789 Factory Road', 10000, 0, 1, 2005, 'INDUSTRIAL', 'ACTIVE'),
('321 Pine Avenue', 1800, 2, 2, 2018, 'RESIDENTIAL', 'ACTIVE'),
('654 Main Street', 3500, 0, 2, 2012, 'COMMERCIAL', 'ACTIVE');

-- Create Indexes for Performance
CREATE INDEX idx_properties_address ON properties(address);
CREATE INDEX idx_predictions_date_range ON prediction_results(prediction_date);

-- View for Property Statistics
CREATE OR REPLACE VIEW property_statistics AS
SELECT 
    property_type,
    COUNT(*) as count,
    AVG(area) as avg_area,
    MIN(year_built) as oldest_year,
    MAX(year_built) as newest_year
FROM properties
GROUP BY property_type;

-- View for Prediction Statistics
CREATE OR REPLACE VIEW prediction_statistics AS
SELECT 
    algorithm,
    COUNT(*) as total_predictions,
    AVG(accuracy) as avg_accuracy,
    MIN(predicted_price) as min_price,
    MAX(predicted_price) as max_price
FROM prediction_results
WHERE status = 'COMPLETED'
GROUP BY algorithm;
