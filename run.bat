@echo off
REM Run the House Price Prediction Web Server

echo ======================================
echo House Price Prediction - Web Server
echo ======================================
echo.

if not exist bin\classes\com\houseprice\SimpleHttpServer.class (
    echo ERROR: SimpleHttpServer not compiled!
    echo Please run build.bat first
    exit /b 1
)

echo Starting web server...
echo.
echo Access the application at: http://localhost:9000
echo.
echo Features:
echo   - Home:      http://localhost:9000
echo   - Predict:   http://localhost:9000/predict
echo   - API:       http://localhost:9000/api/properties
echo.
echo Press Ctrl+C to stop the server
echo.

cd /d "%~dp0"
java -cp bin\classes com.houseprice.SimpleHttpServer

pause
