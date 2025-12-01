@echo off
REM Run the House Price Prediction Application

echo ======================================
echo Running House Price Prediction System
echo ======================================
echo.

if not exist bin\output\HousePricePrediction.jar (
    echo ERROR: JAR file not found!
    echo Please run build.bat first
    exit /b 1
)

echo Starting application...
echo.

java -jar bin\output\HousePricePrediction.jar

pause
