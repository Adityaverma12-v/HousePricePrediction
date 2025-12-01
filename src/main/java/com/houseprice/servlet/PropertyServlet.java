package com.houseprice.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.houseprice.exception.HousePriceException;
import com.houseprice.model.CommercialProperty;
import com.houseprice.model.IndustrialProperty;
import com.houseprice.model.PredictionResult;
import com.houseprice.model.Property;
import com.houseprice.model.ResidentialProperty;
import com.houseprice.service.PricePredictionEngine;
import com.houseprice.service.PropertyService;

/**
 * Property Servlet - Handles HTTP requests for property operations
 */
public class PropertyServlet extends HttpServlet {
    private PropertyService propertyService;
    private PricePredictionEngine predictionEngine;

    @Override
    public void init() throws ServletException {
        propertyService = new PropertyService();
        predictionEngine = new PricePredictionEngine(4); // 4 thread pool
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        String action = request.getParameter("action");

        try {
            if ("list".equals(action)) {
                listProperties(out);
            } else if ("view".equals(action)) {
                String propertyId = request.getParameter("id");
                viewProperty(out, Integer.parseInt(propertyId));
            } else if ("count".equals(action)) {
                getPropertyCount(out);
            } else {
                showMenu(out);
            }
        } catch (Exception e) {
            out.println("<h2>Error: " + e.getMessage() + "</h2>");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        String action = request.getParameter("action");

        try {
            if ("add".equals(action)) {
                addProperty(request, out);
            } else if ("predict".equals(action)) {
                predictPrice(request, out);
            } else if ("delete".equals(action)) {
                deleteProperty(request, out);
            }
        } catch (Exception e) {
            out.println("<h2>Error: " + e.getMessage() + "</h2>");
        }
    }

    private void showMenu(PrintWriter out) {
        out.println("<html>");
        out.println("<head><title>House Price Prediction System</title></head>");
        out.println("<body style='font-family: Arial;'>");
        out.println("<h1>House Price Prediction System</h1>");
        out.println("<h2>Menu</h2>");
        out.println("<ul>");
        out.println("<li><a href='?action=list'>View All Properties</a></li>");
        out.println("<li><a href='#'>Add New Property</a></li>");
        out.println("<li><a href='?action=count'>Total Properties</a></li>");
        out.println("</ul>");
        out.println("</body></html>");
    }

    private void listProperties(PrintWriter out) throws HousePriceException {
        List<Property> properties = propertyService.getAllProperties();
        
        out.println("<html>");
        out.println("<head><title>All Properties</title></head>");
        out.println("<body style='font-family: Arial;'>");
        out.println("<h1>All Properties</h1>");
        out.println("<table border='1' cellpadding='5'>");
        out.println("<tr><th>ID</th><th>Address</th><th>Type</th><th>Area</th><th>Bedrooms</th><th>Price</th></tr>");
        
        for (Property prop : properties) {
            out.println("<tr>");
            out.println("<td>" + prop.getPropertyId() + "</td>");
            out.println("<td>" + prop.getAddress() + "</td>");
            out.println("<td>" + prop.getPropertyType() + "</td>");
            out.println("<td>" + prop.getArea() + "</td>");
            out.println("<td>" + prop.getBedrooms() + "</td>");
            out.println("<td>$" + String.format("%.2f", prop.calculatePrice()) + "</td>");
            out.println("</tr>");
        }
        
        out.println("</table>");
        out.println("<br><a href='?'>Back to Menu</a>");
        out.println("</body></html>");
    }

    private void viewProperty(PrintWriter out, int propertyId) throws HousePriceException {
        Property property = propertyService.getPropertyById(propertyId);
        
        out.println("<html>");
        out.println("<head><title>Property Details</title></head>");
        out.println("<body style='font-family: Arial;'>");
        out.println("<h1>Property Details</h1>");
        out.println("<table border='1' cellpadding='5'>");
        out.println("<tr><td>Property ID:</td><td>" + property.getPropertyId() + "</td></tr>");
        out.println("<tr><td>Address:</td><td>" + property.getAddress() + "</td></tr>");
        out.println("<tr><td>Type:</td><td>" + property.getPropertyType() + "</td></tr>");
        out.println("<tr><td>Area (sq ft):</td><td>" + property.getArea() + "</td></tr>");
        out.println("<tr><td>Bedrooms:</td><td>" + property.getBedrooms() + "</td></tr>");
        out.println("<tr><td>Bathrooms:</td><td>" + property.getBathrooms() + "</td></tr>");
        out.println("<tr><td>Year Built:</td><td>" + property.getYearBuilt() + "</td></tr>");
        out.println("<tr><td>Estimated Price:</td><td>$" + String.format("%.2f", property.calculatePrice()) + "</td></tr>");
        out.println("</table>");
        out.println("<br><a href='?action=list'>Back to Properties</a>");
        out.println("</body></html>");
    }

    private void addProperty(HttpServletRequest request, PrintWriter out) throws HousePriceException {
        String address = request.getParameter("address");
        double area = Double.parseDouble(request.getParameter("area"));
        int bedrooms = Integer.parseInt(request.getParameter("bedrooms"));
        int bathrooms = Integer.parseInt(request.getParameter("bathrooms"));
        int yearBuilt = Integer.parseInt(request.getParameter("yearBuilt"));
        String type = request.getParameter("type");

        Property property;
        if ("RESIDENTIAL".equals(type)) {
            property = new ResidentialProperty(address, area, bedrooms, bathrooms, yearBuilt, 2, true, true);
        } else if ("COMMERCIAL".equals(type)) {
            property = new CommercialProperty(address, area, bedrooms, bathrooms, yearBuilt, 5000, true, 5);
        } else {
            property = new IndustrialProperty(address, area, bedrooms, bathrooms, yearBuilt, 100, true, "INDUSTRIAL");
        }

        propertyService.addProperty(property);

        out.println("<html>");
        out.println("<head><title>Property Added</title></head>");
        out.println("<body>");
        out.println("<h2>Property added successfully!</h2>");
        out.println("<a href='?action=list'>Back to Properties</a>");
        out.println("</body></html>");
    }

    private void predictPrice(HttpServletRequest request, PrintWriter out) throws HousePriceException {
        int propertyId = Integer.parseInt(request.getParameter("propertyId"));
        Property property = propertyService.getPropertyById(propertyId);

        List<PredictionResult> predictions = predictionEngine.predictPrice(property);

        out.println("<html>");
        out.println("<head><title>Price Predictions</title></head>");
        out.println("<body style='font-family: Arial;'>");
        out.println("<h1>Price Predictions for Property ID: " + propertyId + "</h1>");
        out.println("<table border='1' cellpadding='5'>");
        out.println("<tr><th>Algorithm</th><th>Predicted Price</th><th>Accuracy</th></tr>");

        for (PredictionResult pred : predictions) {
            out.println("<tr>");
            out.println("<td>" + pred.getAlgorithm() + "</td>");
            out.println("<td>$" + String.format("%.2f", pred.getPredictedPrice()) + "</td>");
            out.println("<td>" + String.format("%.1f%%", pred.getAccuracy()) + "</td>");
            out.println("</tr>");
            propertyService.savePredictionResult(pred);
        }

        out.println("</table>");
        out.println("<br><a href='?action=list'>Back to Properties</a>");
        out.println("</body></html>");
    }

    private void deleteProperty(HttpServletRequest request, PrintWriter out) throws HousePriceException {
        int propertyId = Integer.parseInt(request.getParameter("id"));
        propertyService.deleteProperty(propertyId);

        out.println("<html>");
        out.println("<head><title>Property Deleted</title></head>");
        out.println("<body>");
        out.println("<h2>Property deleted successfully!</h2>");
        out.println("<a href='?action=list'>Back to Properties</a>");
        out.println("</body></html>");
    }

    private void getPropertyCount(PrintWriter out) throws HousePriceException {
        int count = propertyService.getTotalPropertiesCount();
        
        out.println("<html>");
        out.println("<head><title>Property Count</title></head>");
        out.println("<body>");
        out.println("<h1>Total Properties: " + count + "</h1>");
        out.println("<a href='?'>Back to Menu</a>");
        out.println("</body></html>");
    }

    @Override
    public void destroy() {
        predictionEngine.shutdown();
    }
}
