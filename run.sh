#!/bin/bash
# Run the House Price Prediction Application

echo "======================================"
echo "Running House Price Prediction System"
echo "======================================"
echo ""

if [ ! -f "bin/output/HousePricePrediction.jar" ]; then
    echo "ERROR: JAR file not found!"
    echo "Please run ./build.sh first"
    exit 1
fi

echo "Starting application..."
echo ""

java -jar bin/output/HousePricePrediction.jar
