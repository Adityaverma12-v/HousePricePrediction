#!/bin/bash
# Run the House Price Prediction Web Server

echo "======================================"
echo "House Price Prediction - Web Server"
echo "======================================"
echo ""

if [ ! -f "bin/classes/com/houseprice/SimpleHttpServer.class" ]; then
    echo "ERROR: SimpleHttpServer not compiled!"
    echo "Please run ./build.sh first"
    exit 1
fi

echo "Starting web server..."
echo ""
echo "Access the application at: http://localhost:9000"
echo ""
echo "Features:"
echo "  - Home:      http://localhost:9000"
echo "  - Predict:   http://localhost:9000/predict"
echo "  - API:       http://localhost:9000/api/properties"
echo ""
echo "Press Ctrl+C to stop the server"
echo ""

java -cp bin/classes com.houseprice.SimpleHttpServer
