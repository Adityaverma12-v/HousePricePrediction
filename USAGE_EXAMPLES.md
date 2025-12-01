## Usage Examples & API Reference

### 1. Running the Console Application

```bash
# Navigate to project directory
cd new\ house\ price\ preduction

# Build project
mvn clean compile

# Run the application
mvn exec:java -Dexec.mainClass="com.houseprice.HousePriceApplication"
```

**Expected Output:**
```
=== House Price Prediction System ===
Starting application...

Creating sample properties...
Adding properties to database...

=== All Properties ===
Property{propertyId=1, address='123 Oak Street', area=2000.0, 
bedrooms=3, bathrooms=2, yearBuilt=2015, type='RESIDENTIAL', 
estimatedPrice=260000.0}

=== Price Statistics ===
Total Properties: 3
Average Price: $285000.00

=== Concurrent Price Predictions (Multithreading) ===
Predicting prices for: 123 Oak Street
Algorithm: LINEAR_REGRESSION | Price: $273000.00 | Accuracy: 92.5%
Algorithm: PROPERTY_FEATURES | Price: $245000.00 | Accuracy: 88.3%
Algorithm: MARKET_ANALYSIS | Price: $299000.00 | Accuracy: 85.7%
```

### 2. Using the Web Application

#### Start Tomcat:
```bash
$TOMCAT_HOME/bin/startup.sh
```

#### Access the application:
```
http://localhost:8080/house-price-prediction-1.0-SNAPSHOT
```

#### Available Endpoints:

**GET /property** - Home page with menu
```
http://localhost:8080/house-price-prediction-1.0-SNAPSHOT/property
```

**GET /property?action=list** - List all properties
```
http://localhost:8080/house-price-prediction-1.0-SNAPSHOT/property?action=list
```

**GET /property?action=view&id=1** - View specific property
```
http://localhost:8080/house-price-prediction-1.0-SNAPSHOT/property?action=view&id=1
```

**GET /property?action=count** - Get total properties
```
http://localhost:8080/house-price-prediction-1.0-SNAPSHOT/property?action=count
```

**POST /property?action=add** - Add new property
```html
<form method="POST" action="/house-price-prediction-1.0-SNAPSHOT/property">
  <input type="hidden" name="action" value="add">
  <input type="text" name="address" placeholder="Address">
  <input type="number" name="area" placeholder="Area (sq ft)">
  <input type="number" name="bedrooms" placeholder="Bedrooms">
  <input type="number" name="bathrooms" placeholder="Bathrooms">
  <input type="number" name="yearBuilt" placeholder="Year Built">
  <select name="type">
    <option value="RESIDENTIAL">Residential</option>
    <option value="COMMERCIAL">Commercial</option>
    <option value="INDUSTRIAL">Industrial</option>
  </select>
  <button type="submit">Add Property</button>
</form>
```

**POST /property?action=predict** - Get price predictions
```html
<form method="POST" action="/house-price-prediction-1.0-SNAPSHOT/property">
  <input type="hidden" name="action" value="predict">
  <input type="number" name="propertyId" placeholder="Property ID">
  <button type="submit">Get Predictions</button>
</form>
```

### 3. Using PropertyService Programmatically

```java
// Initialize service
PropertyService service = new PropertyService();

// Add a residential property
ResidentialProperty property = new ResidentialProperty(
    "123 Test Street",
    2000,      // area
    3,         // bedrooms
    2,         // bathrooms
    2015,      // year built
    2,         // floors
    true,      // has garage
    true       // has garden
);
service.addProperty(property);

// Get all properties
List<Property> allProperties = service.getAllProperties();
for (Property p : allProperties) {
    System.out.println(p.getAddress() + " - $" + p.calculatePrice());
}

// Get specific property
Property prop = service.getPropertyById(1);

// Update property
prop.setAddress("Updated Address");
service.updateProperty(prop);

// Get properties by type
List<ResidentialProperty> residential = 
    service.getPropertiesByType(ResidentialProperty.class);

// Calculate statistics
double avgPrice = service.getAveragePredictedPrice();
int totalCount = service.getTotalPropertiesCount();

// Delete property
service.deleteProperty(1);
```

### 4. Using PricePredictionEngine

```java
// Create engine with thread pool size
PricePredictionEngine engine = new PricePredictionEngine(4);

// Get property
PropertyService service = new PropertyService();
Property property = service.getPropertyById(1);

// Get concurrent predictions
List<PredictionResult> predictions = engine.predictPrice(property);

// Process results
for (PredictionResult pred : predictions) {
    System.out.println(pred.getAlgorithm() + ": " + pred.getPredictedPrice());
}

// Cache operations
List<PredictionResult> cached = engine.getCachedPredictions(1);
engine.clearCache();

// Cleanup
engine.shutdown();
```

### 5. Exception Handling

```java
try {
    PropertyService service = new PropertyService();
    
    // This will throw ValidationException
    ResidentialProperty invalid = new ResidentialProperty();
    invalid.setAddress("");  // Invalid
    service.addProperty(invalid);
    
} catch (ValidationException e) {
    System.err.println("Validation Error: " + e.getMessage());
    System.err.println("Error Code: " + e.getErrorCode());
    
} catch (DatabaseException e) {
    System.err.println("Database Error: " + e.getMessage());
    
} catch (HousePriceException e) {
    System.err.println("Application Error: " + e.getMessage());
    e.printStackTrace();
}
```

### 6. Working with Generic Repository

```java
// PropertyDAO implements IRepository<Property>
PropertyDAO dao = new PropertyDAO();

// Save property
Property property = new ResidentialProperty(...);
dao.save(property);

// Find by ID
Property found = dao.findById(1);

// Get all
List<Property> all = dao.findAll();

// Update
dao.update(property);

// Delete
dao.delete(1);

// Count
int total = dao.count();
```

### 7. Filtering Properties with Collections

```java
PropertyService service = new PropertyService();

// Get all properties
List<Property> properties = service.getAllProperties();

// Filter by bedroom count using streams
List<Property> threeBedroom = properties.stream()
    .filter(p -> p.getBedrooms() == 3)
    .collect(Collectors.toList());

// Map to prices
List<Double> prices = properties.stream()
    .map(Property::calculatePrice)
    .collect(Collectors.toList());

// Calculate statistics
double avgPrice = prices.stream()
    .mapToDouble(Double::doubleValue)
    .average()
    .orElse(0);

// Sort by price
List<Property> sorted = properties.stream()
    .sorted((p1, p2) -> Double.compare(
        p1.calculatePrice(), 
        p2.calculatePrice()))
    .collect(Collectors.toList());
```

### 8. Database Operations

```java
// Raw JDBC (advanced)
DatabaseConnection dbConnection = DatabaseConnection.getInstance();
Connection conn = dbConnection.getConnection();

// Use connection for prepared statements
String query = "SELECT * FROM properties WHERE bedrooms = ?";
PreparedStatement pstmt = conn.prepareStatement(query);
pstmt.setInt(1, 3);
ResultSet rs = pstmt.executeQuery();

while (rs.next()) {
    System.out.println(rs.getString("address"));
}

// Clean up
rs.close();
pstmt.close();
```

### 9. Testing

```bash
# Run all tests
mvn test

# Run specific test class
mvn test -Dtest=PropertyServiceTest

# Run specific test method
mvn test -Dtest=PropertyServiceTest#testPolymorphism

# Run with coverage
mvn test jacoco:report
```

### 10. Building and Deployment

```bash
# Compile only
mvn compile

# Package as JAR
mvn package -DskipTests -Djar.skipIfEmpty=true

# Package as WAR (for Tomcat)
mvn package -DskipTests -Dwar.skipIfEmpty=true

# Deploy to Tomcat
mvn tomcat7:deploy

# Undeploy
mvn tomcat7:undeploy

# Create executable JAR with dependencies
mvn assembly:assembly -DdescriptorId=jar-with-dependencies
```

### 11. Performance Tips

```java
// Use generics for type safety
List<Property> properties = new ArrayList<>();  // ✓ Good
List properties = new ArrayList();              // ✗ Avoid

// Use try-with-resources
try (Connection conn = dbConnection.getConnection();
     PreparedStatement pstmt = conn.prepareStatement(sql)) {
    // Operations
} // Automatically closed

// Use synchronized blocks strategically
synchronized (cacheLock) {
    predictionCache.add(result);
}

// Configure thread pool appropriately
PricePredictionEngine engine = new PricePredictionEngine(4);

// Use streams for efficient filtering
properties.stream()
    .filter(condition)
    .map(transformation)
    .collect(result);
```

### 12. Troubleshooting Common Issues

**Issue: Connection Refused**
```java
// Check if MySQL is running and configure correctly
DatabaseConnection dbConn = DatabaseConnection.getInstance();
if (dbConn.testConnection()) {
    System.out.println("Connection successful!");
} else {
    System.out.println("Connection failed!");
}
```

**Issue: Property Not Found**
```java
try {
    Property prop = service.getPropertyById(999);
} catch (ValidationException e) {
    System.out.println("Property not found: " + e.getMessage());
}
```

**Issue: Prediction Timeout**
```java
try {
    List<PredictionResult> predictions = engine.predictPrice(property);
} catch (HousePriceException e) {
    System.out.println("Prediction timeout or error: " + e.getMessage());
}
```

---

For more details, refer to README.md and ARCHITECTURE.md
