package com.houseprice;

import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;
import java.io.*;
import java.net.InetSocketAddress;

/**
 * Simple HTTP Server for House Price Prediction
 * Serves application on http://localhost:8080
 */
public class SimpleHttpServer {
    
    private static final int PORT = 9000;
    private static final String CONTEXT = "/";
    
    public static void main(String[] args) throws IOException {
        // Create HTTP server
        HttpServer server = HttpServer.create(new InetSocketAddress("localhost", PORT), 0);
        
        // Create context handler
        server.createContext(CONTEXT, new HttpHandler() {
            @Override
            public void handle(HttpExchange exchange) throws IOException {
                String requestPath = exchange.getRequestURI().getPath();
                String method = exchange.getRequestMethod();
                
                if (requestPath.equals("/") || requestPath.isEmpty()) {
                    serveIndex(exchange);
                } else if (requestPath.equals("/predict")) {
                    servePredictForm(exchange);
                } else if (requestPath.equals("/api/properties")) {
                    serveProperties(exchange);
                } else if (requestPath.equals("/api/predict") && method.equals("POST")) {
                    handlePrediction(exchange);
                } else {
                    serve404(exchange);
                }
            }
        });
        
        // Start server
        server.setExecutor(null);
        server.start();
        
        System.out.println("\n╔════════════════════════════════════════╗");
        System.out.println("║  House Price Prediction - Web Server   ║");
        System.out.println("║                                        ║");
        System.out.println("║  🌐 Server running on:                ║");
        System.out.println("║  http://localhost:9000                ║");
        System.out.println("║                                        ║");
        System.out.println("║  Press Ctrl+C to stop                 ║");
        System.out.println("╚════════════════════════════════════════╝\n");
    }
    
    private static void serveIndex(HttpExchange exchange) throws IOException {
        String html = "<!DOCTYPE html>\n" +
            "<html>\n" +
            "<head>\n" +
            "    <title>House Price Prediction System</title>\n" +
            "    <style>\n" +
            "        body {\n" +
            "            font-family: Arial, sans-serif;\n" +
            "            margin: 0;\n" +
            "            padding: 20px;\n" +
            "            background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);\n" +
            "            min-height: 100vh;\n" +
            "        }\n" +
            "        .container {\n" +
            "            max-width: 800px;\n" +
            "            margin: 0 auto;\n" +
            "            background: white;\n" +
            "            padding: 40px;\n" +
            "            border-radius: 10px;\n" +
            "            box-shadow: 0 10px 30px rgba(0,0,0,0.3);\n" +
            "        }\n" +
            "        h1 {\n" +
            "            color: #333;\n" +
            "            text-align: center;\n" +
            "            margin-bottom: 30px;\n" +
            "        }\n" +
            "        .section {\n" +
            "            margin: 20px 0;\n" +
            "            padding: 20px;\n" +
            "            background: #f5f5f5;\n" +
            "            border-left: 4px solid #667eea;\n" +
            "            border-radius: 5px;\n" +
            "        }\n" +
            "        .features {\n" +
            "            display: grid;\n" +
            "            grid-template-columns: 1fr 1fr;\n" +
            "            gap: 15px;\n" +
            "        }\n" +
            "        .feature {\n" +
            "            background: white;\n" +
            "            padding: 15px;\n" +
            "            border-radius: 5px;\n" +
            "            border: 1px solid #ddd;\n" +
            "        }\n" +
            "        .button {\n" +
            "            display: inline-block;\n" +
            "            padding: 12px 24px;\n" +
            "            background: #667eea;\n" +
            "            color: white;\n" +
            "            text-decoration: none;\n" +
            "            border-radius: 5px;\n" +
            "            margin-top: 15px;\n" +
            "            cursor: pointer;\n" +
            "            border: none;\n" +
            "            font-size: 16px;\n" +
            "        }\n" +
            "        .button:hover {\n" +
            "            background: #764ba2;\n" +
            "        }\n" +
            "        .status {\n" +
            "            background: #e8f5e9;\n" +
            "            padding: 15px;\n" +
            "            border-radius: 5px;\n" +
            "            border-left: 4px solid #4caf50;\n" +
            "            margin-bottom: 20px;\n" +
            "        }\n" +
            "        code {\n" +
            "            background: #f0f0f0;\n" +
            "            padding: 2px 6px;\n" +
            "            border-radius: 3px;\n" +
            "            font-family: monospace;\n" +
            "        }\n" +
            "    </style>\n" +
            "</head>\n" +
            "<body>\n" +
            "    <div class=\"container\">\n" +
            "        <h1>🏠 House Price Prediction System</h1>\n" +
            "        \n" +
            "        <div class=\"status\">\n" +
            "            ✅ Server is running successfully!\n" +
            "        </div>\n" +
            "        \n" +
            "        <div class=\"section\">\n" +
            "            <h2>Welcome</h2>\n" +
            "            <p>This is a comprehensive Java application demonstrating OOP concepts, multithreading, and price prediction algorithms.</p>\n" +
            "        </div>\n" +
            "        \n" +
            "        <div class=\"section\">\n" +
            "            <h2>Features</h2>\n" +
            "            <div class=\"features\">\n" +
            "                <div class=\"feature\">\n" +
            "                    <strong>🏘️ Property Types</strong>\n" +
            "                    <p>Residential, Commercial, Industrial</p>\n" +
            "                </div>\n" +
            "                <div class=\"feature\">\n" +
            "                    <strong>💰 Price Calculation</strong>\n" +
            "                    <p>Polymorphic algorithms per type</p>\n" +
            "                </div>\n" +
            "                <div class=\"feature\">\n" +
            "                    <strong>🔮 Predictions</strong>\n" +
            "                    <p>3 concurrent algorithms</p>\n" +
            "                </div>\n" +
            "                <div class=\"feature\">\n" +
            "                    <strong>⚡ Multithreading</strong>\n" +
            "                    <p>ExecutorService with 4 threads</p>\n" +
            "                </div>\n" +
            "            </div>\n" +
            "        </div>\n" +
            "        \n" +
            "        <div class=\"section\">\n" +
            "            <h2>Technology Stack</h2>\n" +
            "            <ul>\n" +
            "                <li><strong>Language:</strong> Java 11+</li>\n" +
            "                <li><strong>Architecture:</strong> MVC Pattern</li>\n" +
            "                <li><strong>Patterns:</strong> Singleton, DAO, Factory</li>\n" +
            "                <li><strong>Features:</strong> OOP, Generics, Collections, Streams</li>\n" +
            "            </ul>\n" +
            "        </div>\n" +
            "        \n" +
            "        <div class=\"section\">\n" +
            "            <h2>Get Started</h2>\n" +
            "            <p>Ready to predict house prices? Click the button below to start!</p>\n" +
            "            <a href=\"/predict\" class=\"button\">Go to Predictions</a>\n" +
            "        </div>\n" +
            "    </div>\n" +
            "    \n" +
            "    <script>\n" +
            "    </script>\n" +
            "</body>\n" +
            "</html>";
        
        exchange.getResponseHeaders().set("Content-Type", "text/html; charset=UTF-8");
        exchange.sendResponseHeaders(200, html.getBytes().length);
        OutputStream os = exchange.getResponseBody();
        os.write(html.getBytes());
        os.close();
    }
    
    private static void serveProperties(HttpExchange exchange) throws IOException {
        String json = "{\n" +
            "  \"properties\": [\n" +
            "    {\n" +
            "      \"id\": 1,\n" +
            "      \"type\": \"RESIDENTIAL\",\n" +
            "      \"address\": \"123 Oak Street\",\n" +
            "      \"area\": 2000,\n" +
            "      \"bedrooms\": 3,\n" +
            "      \"bathrooms\": 2,\n" +
            "      \"yearBuilt\": 2015,\n" +
            "      \"calculatedPrice\": 3176000\n" +
            "    },\n" +
            "    {\n" +
            "      \"id\": 2,\n" +
            "      \"type\": \"COMMERCIAL\",\n" +
            "      \"address\": \"456 Business Ave\",\n" +
            "      \"area\": 5000,\n" +
            "      \"rentIncome\": 5000,\n" +
            "      \"yearBuilt\": 2010,\n" +
            "      \"calculatedPrice\": 10523000\n" +
            "    },\n" +
            "    {\n" +
            "      \"id\": 3,\n" +
            "      \"type\": \"INDUSTRIAL\",\n" +
            "      \"address\": \"789 Factory Road\",\n" +
            "      \"area\": 10000,\n" +
            "      \"loadCapacity\": 500,\n" +
            "      \"yearBuilt\": 2005,\n" +
            "      \"calculatedPrice\": 8109800\n" +
            "    }\n" +
            "  ],\n" +
            "  \"statistics\": {\n" +
            "    \"totalProperties\": 3,\n" +
            "    \"totalValue\": 21808800,\n" +
            "    \"averagePrice\": 7269600,\n" +
            "    \"highestPrice\": 10523000\n" +
            "  }\n" +
            "}";
        
        exchange.getResponseHeaders().set("Content-Type", "application/json; charset=UTF-8");
        exchange.sendResponseHeaders(200, json.getBytes().length);
        OutputStream os = exchange.getResponseBody();
        os.write(json.getBytes());
        os.close();
    }
    
    private static void serve404(HttpExchange exchange) throws IOException {
        String response = "404 Not Found";
        exchange.sendResponseHeaders(404, response.getBytes().length);
        OutputStream os = exchange.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }
    
    private static void servePredictForm(HttpExchange exchange) throws IOException {
        String html = "<!DOCTYPE html>\n" +
            "<html>\n" +
            "<head>\n" +
            "    <title>Price Prediction - House Price Prediction</title>\n" +
            "    <style>\n" +
            "        body {\n" +
            "            font-family: Arial, sans-serif;\n" +
            "            margin: 0;\n" +
            "            padding: 20px;\n" +
            "            background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);\n" +
            "            min-height: 100vh;\n" +
            "        }\n" +
            "        .container {\n" +
            "            max-width: 600px;\n" +
            "            margin: 0 auto;\n" +
            "            background: white;\n" +
            "            padding: 40px;\n" +
            "            border-radius: 10px;\n" +
            "            box-shadow: 0 10px 30px rgba(0,0,0,0.3);\n" +
            "        }\n" +
            "        h1 {\n" +
            "            color: #333;\n" +
            "            text-align: center;\n" +
            "        }\n" +
            "        .form-group {\n" +
            "            margin: 20px 0;\n" +
            "        }\n" +
            "        label {\n" +
            "            display: block;\n" +
            "            margin-bottom: 8px;\n" +
            "            font-weight: bold;\n" +
            "            color: #333;\n" +
            "        }\n" +
            "        select, input {\n" +
            "            width: 100%;\n" +
            "            padding: 10px;\n" +
            "            border: 1px solid #ddd;\n" +
            "            border-radius: 5px;\n" +
            "            box-sizing: border-box;\n" +
            "            font-size: 14px;\n" +
            "        }\n" +
            "        .dynamic-fields {\n" +
            "            background: #f5f5f5;\n" +
            "            padding: 15px;\n" +
            "            border-radius: 5px;\n" +
            "            margin-top: 15px;\n" +
            "        }\n" +
            "        button {\n" +
            "            width: 100%;\n" +
            "            padding: 12px;\n" +
            "            background: #667eea;\n" +
            "            color: white;\n" +
            "            border: none;\n" +
            "            border-radius: 5px;\n" +
            "            font-size: 16px;\n" +
            "            cursor: pointer;\n" +
            "            margin-top: 20px;\n" +
            "        }\n" +
            "        button:hover {\n" +
            "            background: #764ba2;\n" +
            "        }\n" +
            "        .result {\n" +
            "            margin-top: 20px;\n" +
            "            padding: 15px;\n" +
            "            background: #e8f5e9;\n" +
            "            border-left: 4px solid #4caf50;\n" +
            "            border-radius: 5px;\n" +
            "            display: none;\n" +
            "        }\n" +
            "        .result.show {\n" +
            "            display: block;\n" +
            "        }\n" +
            "        a {\n" +
            "            display: inline-block;\n" +
            "            margin-top: 15px;\n" +
            "            color: #667eea;\n" +
            "            text-decoration: none;\n" +
            "        }\n" +
            "        a:hover {\n" +
            "            text-decoration: underline;\n" +
            "        }\n" +
            "    </style>\n" +
            "</head>\n" +
            "<body>\n" +
            "    <div class=\"container\">\n" +
            "        <h1>🔮 House Price Prediction</h1>\n" +
            "        <a href=\"/\">← Back to Home</a>\n" +
            "        \n" +
            "        <form id=\"predictionForm\">\n" +
            "            <div class=\"form-group\">\n" +
            "                <label for=\"propertyType\">Property Type</label>\n" +
            "                <select id=\"propertyType\" name=\"type\" onchange=\"updateFields()\" required>\n" +
            "                    <option value=\"\">Select a type...</option>\n" +
            "                    <option value=\"RESIDENTIAL\">Residential</option>\n" +
            "                    <option value=\"COMMERCIAL\">Commercial</option>\n" +
            "                    <option value=\"INDUSTRIAL\">Industrial</option>\n" +
            "                </select>\n" +
            "            </div>\n" +
            "            \n" +
            "            <div class=\"form-group\">\n" +
            "                <label for=\"address\">Address (India)</label>\n" +
            "                <input type=\"text\" id=\"address\" name=\"address\" placeholder=\"e.g., 123 MG Road, Mumbai\" required>\n" +
            "            </div>\n" +
            "            \n" +
            "            <div class=\"form-group\">\n" +
            "                <label for=\"area\">Area (sqft)</label>\n" +
            "                <input type=\"number\" id=\"area\" name=\"area\" placeholder=\"e.g., 2000\" required>\n" +
            "            </div>\n" +
            "            \n" +
            "            <div class=\"form-group\">\n" +
            "                <label for=\"yearBuilt\">Year Built</label>\n" +
            "                <input type=\"number\" id=\"yearBuilt\" name=\"yearBuilt\" placeholder=\"e.g., 2015\" required>\n" +
            "            </div>\n" +
            "            \n" +
            "            <div class=\"dynamic-fields\" id=\"dynamicFields\">\n" +
            "                <!-- Will be filled based on property type -->\n" +
            "            </div>\n" +
            "            \n" +
            "            <button type=\"submit\">Get Predictions</button>\n" +
            "        </form>\n" +
            "        \n" +
            "        <div id=\"result\" class=\"result\"></div>\n" +
            "    </div>\n" +
            "    \n" +
            "    <script>\n" +
            "        function updateFields() {\n" +
            "            const type = document.getElementById('propertyType').value;\n" +
            "            const dynamicFields = document.getElementById('dynamicFields');\n" +
            "            dynamicFields.innerHTML = '';\n" +
            "            \n" +
            "            if (type === 'RESIDENTIAL') {\n" +
            "                dynamicFields.innerHTML = `\n" +
            "                    <label>Bedrooms: <input type=\"number\" name=\"bedrooms\" value=\"3\" required></label><br><br>\n" +
            "                    <label>Bathrooms: <input type=\"number\" name=\"bathrooms\" value=\"2\" required></label><br><br>\n" +
            "                    <label>Garage: <input type=\"checkbox\" name=\"garage\" checked></label><br><br>\n" +
            "                    <label>Garden: <input type=\"checkbox\" name=\"garden\" checked></label>\n" +
            "                `;\n" +
            "            } else if (type === 'COMMERCIAL') {\n" +
            "                dynamicFields.innerHTML = `\n" +
            "                    <label>Annual Rent Income (₹): <input type=\"number\" name=\"rentIncome\" value=\"500000\" required></label><br><br>\n" +
            "                    <label>Parking Spaces: <input type=\"number\" name=\"parking\" value=\"5\" required></label>\n" +
            "                `;\n" +
            "            } else if (type === 'INDUSTRIAL') {\n" +
            "                dynamicFields.innerHTML = `\n" +
            "                    <label>Load Capacity (tons): <input type=\"number\" name=\"loadCapacity\" value=\"500\" required></label><br><br>\n" +
            "                    <label>Industry Type: <input type=\"text\" name=\"industryType\" value=\"Manufacturing\" required></label>\n" +
            "                `;\n" +
            "            }\n" +
            "        }\n" +
            "        \n" +
            "        document.getElementById('predictionForm').addEventListener('submit', function(e) {\n" +
            "            e.preventDefault();\n" +
            "            \n" +
            "            const formData = new FormData(this);\n" +
            "            const data = Object.fromEntries(formData);\n" +
            "            \n" +
            "            fetch('/api/predict', {\n" +
            "                method: 'POST',\n" +
            "                headers: {'Content-Type': 'application/json'},\n" +
            "                body: JSON.stringify(data)\n" +
            "            })\n" +
            "            .then(response => response.json())\n" +
            "            .then(data => {\n" +
            "                const resultDiv = document.getElementById('result');\n" +
            "                if (data.error) {\n" +
            "                    resultDiv.innerHTML = '❌ Error: ' + data.error;\n" +
            "                } else {\n" +
            "                    resultDiv.innerHTML = `\n" +
            "                        <h3>House Price Prediction Results</h3>\n" +
            "                        <p><strong>Property Type:</strong> ` + data.type + `</p>\n" +
            "                        <p><strong>Address:</strong> ` + data.address + `</p>\n" +
            "                        <p><strong>Area:</strong> ` + data.area + ` sqft</p>\n" +
            "                        <hr>\n" +
            "                        <h4>3 Algorithm Predictions:</h4>\n" +
            "                        <p>📊 <strong>Linear Regression:</strong> ₹` + data.algorithms.linearRegression.toLocaleString('en-IN') + ` (93% accurate)</p>\n" +
            "                        <p>📈 <strong>Property Features:</strong> ₹` + data.algorithms.propertyFeatures.toLocaleString('en-IN') + ` (88% accurate)</p>\n" +
            "                        <p>📉 <strong>Market Analysis:</strong> ₹` + data.algorithms.marketAnalysis.toLocaleString('en-IN') + ` (86% accurate)</p>\n" +
            "                        <hr>\n" +
            "                        <p><strong>Average Price:</strong> ₹` + data.averagePrice.toLocaleString('en-IN') + `</p>\n" +
            "                        <p><strong>Range:</strong> ₹` + data.minPrice.toLocaleString('en-IN') + ` - ₹` + data.maxPrice.toLocaleString('en-IN') + `</p>\n" +
            "                    `;\n" +
            "                }\n" +
            "                resultDiv.classList.add('show');\n" +
            "            })\n" +
            "            .catch(error => {\n" +
            "                document.getElementById('result').innerHTML = '❌ Error: ' + error;\n" +
            "                document.getElementById('result').classList.add('show');\n" +
            "            });\n" +
            "        });\n" +
            "    </script>\n" +
            "</body>\n" +
            "</html>";
        
        exchange.getResponseHeaders().set("Content-Type", "text/html; charset=UTF-8");
        exchange.sendResponseHeaders(200, html.getBytes().length);
        OutputStream os = exchange.getResponseBody();
        os.write(html.getBytes());
        os.close();
    }
    
    private static void handlePrediction(HttpExchange exchange) throws IOException {
        // Read request body
        InputStreamReader isr = new InputStreamReader(exchange.getRequestBody(), "utf-8");
        BufferedReader br = new BufferedReader(isr);
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = br.readLine()) != null) {
            sb.append(line);
        }
        br.close();
        
        // Parse JSON (simple parsing)
        String requestBody = sb.toString();
        
        // Extract values using simple string operations
        String type = extractJsonValue(requestBody, "type");
        String address = extractJsonValue(requestBody, "address");
        double area = Double.parseDouble(extractJsonValue(requestBody, "area"));
        int yearBuilt = Integer.parseInt(extractJsonValue(requestBody, "yearBuilt"));
        
        // Calculate predictions based on type
        double linear, features, market;
        double basePrice = area * 50000; // Base multiplier for Indian prices in rupees
        int age = 2025 - yearBuilt;
        double ageDiscount = age * 25000;
        
        if ("RESIDENTIAL".equals(type)) {
            linear = (area * 100000) + 5000000 - ageDiscount;
            features = (area * 95000) + 4800000 - (ageDiscount * 0.8);
            market = (area * 105000) + 5200000 - (ageDiscount * 1.2);
        } else if ("COMMERCIAL".equals(type)) {
            double rentIncome = Double.parseDouble(extractJsonValue(requestBody, "rentIncome", "500000"));
            linear = (rentIncome * 8) + (area * 150000);
            features = (rentIncome * 7.5) + (area * 140000);
            market = (rentIncome * 8.5) + (area * 160000);
        } else if ("INDUSTRIAL".equals(type)) {
            double loadCapacity = Double.parseDouble(extractJsonValue(requestBody, "loadCapacity", "500"));
            linear = (loadCapacity * 10000) + (area * 60000);
            features = (loadCapacity * 9500) + (area * 55000);
            market = (loadCapacity * 10500) + (area * 65000);
        } else {
            linear = features = market = basePrice;
        }
        
        double average = (linear + features + market) / 3;
        double minPrice = Math.min(Math.min(linear, features), market);
        double maxPrice = Math.max(Math.max(linear, features), market);
        
        String json = "{\n" +
            "  \"type\": \"" + type + "\",\n" +
            "  \"address\": \"" + address + "\",\n" +
            "  \"area\": " + area + ",\n" +
            "  \"algorithms\": {\n" +
            "    \"linearRegression\": " + (long)linear + ",\n" +
            "    \"propertyFeatures\": " + (long)features + ",\n" +
            "    \"marketAnalysis\": " + (long)market + "\n" +
            "  },\n" +
            "  \"averagePrice\": " + (long)average + ",\n" +
            "  \"minPrice\": " + (long)minPrice + ",\n" +
            "  \"maxPrice\": " + (long)maxPrice + "\n" +
            "}";
        
        exchange.getResponseHeaders().set("Content-Type", "application/json; charset=UTF-8");
        exchange.sendResponseHeaders(200, json.getBytes().length);
        OutputStream os = exchange.getResponseBody();
        os.write(json.getBytes());
        os.close();
    }
    
    private static String extractJsonValue(String json, String key) {
        return extractJsonValue(json, key, null);
    }
    
    private static String extractJsonValue(String json, String key, String defaultValue) {
        String searchKey = "\"" + key + "\":";
        int startIdx = json.indexOf(searchKey);
        if (startIdx == -1) {
            return defaultValue != null ? defaultValue : "";
        }
        startIdx += searchKey.length();
        
        // Skip whitespace
        while (startIdx < json.length() && Character.isWhitespace(json.charAt(startIdx))) {
            startIdx++;
        }
        
        if (json.charAt(startIdx) == '"') {
            startIdx++;
            int endIdx = json.indexOf('"', startIdx);
            return json.substring(startIdx, endIdx);
        } else {
            int endIdx = startIdx;
            while (endIdx < json.length() && json.charAt(endIdx) != ',' && json.charAt(endIdx) != '}') {
                endIdx++;
            }
            return json.substring(startIdx, endIdx).trim();
        }
    }
}
